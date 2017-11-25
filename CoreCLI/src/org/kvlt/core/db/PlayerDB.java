package org.kvlt.core.db;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.entities.SimplePlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.models.*;
import org.kvlt.core.utils.Log;
import org.sql2o.Connection;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Для управления записями игрокамов в БД
 */
public class PlayerDB {

    public static ExecutorService executor;

    static {
        executor = Executors.newSingleThreadExecutor();
    }

    public static void save(OnlinePlayer player) {
        int id = player.getId();
        long playedNow = PlayedTimeCounter.stop(player);

        PlayedTimeCounter.stop(player);
        CoreServer.get().getOnlinePlayers().remove(player);

        String sql1 = "UPDATE join_info SET online_time = online_time + :now WHERE id = :id";
        String sql2 = "UPDATE join_info SET last_online = :now WHERE  id = :id";
        String sql3 = "UPDATE join_info SET ip = :ip WHERE  id = :id";
        String sql4 = "UPDATE join_info SET server = :server WHERE  id = :id";

        Runnable r = () -> {
            DAO.getConnection()
                    .createQuery(sql1)
                    .addParameter("id", id)
                    .addParameter("now", playedNow)
                    .executeUpdate();
            DAO.getConnection()
                    .createQuery(sql2)
                    .addParameter("id", id)
                    .addParameter("now", playedNow)
                    .executeUpdate();
            DAO.getConnection()
                    .createQuery(sql3)
                    .addParameter("id", id)
                    .addParameter("ip", player.getIp())
                    .executeUpdate();
            DAO.getConnection()
                    .createQuery(sql4)
                    .addParameter("id", id)
                    .addParameter("server", player.getCurrentServer().getName())
                    .executeUpdate();
        };
        executor.execute(r);
    }

    public static void register(OnlinePlayer player, String password) {
        Runnable r = () -> {
            int id = player.getId();
            String ip = player.getIp();
            long now = System.currentTimeMillis();

            String registerSql = "UPDATE authentication SET\n" +
                    "password = :pass,\n" +
                    "registration_ip = :ip,\n" +
                    "last_authenticated = :now\n" +
                    "WHERE id = :id";

            DAO.getConnection()
                    .createQuery(registerSql)
                    .addParameter("pass", password)
                    .addParameter("ip", ip)
                    .addParameter("now", now)
                    .addParameter("id", id)
                    .executeUpdate();
        };

        executor.execute(r);
    }

    public static boolean correctPassword(OnlinePlayer op, String password) {
        return op.getPassword() != null && op.getPassword().equals(password);
    }

    public static int loadId(String name) {
        int id;
        String existSql = "SELECT IF(COUNT(id) = 0, 0, id) AS id FROM identifier WHERE player_name = :name";

        id = DAO.getConnection()
                .createQuery(existSql)
                .addParameter("name", name)
                .executeScalar(Integer.class);
        return id;
    }

    public static void loadOnlinePlayer(OnlinePlayer player) {
        Runnable r = () -> {
            int id = loadId(player.getName());

            if (id == 0) {
                createPlayerModel(player);
            } else {
                player.setId(id);
                loadPlayerModel(player);
            }

            PlayedTimeCounter.start(player);
        };

        executor.execute(r);
    }

    public static ServerPlayer loadServerPlayer(String name) {
        ServerPlayer player = new SimplePlayer(name);
        int id = loadId(name);

        player.setId(id);
        loadPlayerModel(player);

        return player;
    }

    private static void createPlayerModel(OnlinePlayer player) {
        String playerName = player.getName();
        String insertIdSql = "INSERT INTO identifier (player_name) VALUES (:name) ON DUPLICATE KEY UPDATE id=id";

        BigInteger bigId = (BigInteger) DAO.getConnection()
                .createQuery(insertIdSql, true)
                .addParameter("name", playerName)
                .executeUpdate()
                .getKey();

        int id = bigId.intValue();

        String[] queries = {
                "INSERT INTO join_info (id) VALUES (:id)",
                "INSERT INTO authentication (id) VALUES (:id)",
                "INSERT INTO friends (id) VALUES (:id)",
                "INSERT INTO ignores (id) VALUES (:id)",
                "INSERT INTO infractions (id) VALUES (:id)",
                "INSERT INTO players_groups (id) VALUES (:id)",
                "INSERT INTO reporters (id) VALUES (:id)",
                "INSERT INTO selected_skins (id) VALUES (:id)",
                "INSERT INTO settings (id) VALUES (:id)"
        };

        for (String q : queries) {
            executor.execute(() -> {
                DAO.getConnection().createQuery(q).addParameter("id", id).executeUpdate();
            });
        }

        player.setId(id);

    }

    private static void loadPlayerModel(ServerPlayer player) {
        if (player == null) return;

        int id = player.getId();
        Connection conn = DAO.getConnection();

        AuthModel authModel = loadModel(AuthModel.class, new AuthParams(), conn, id);
        JoinInfoModel joinInfoModel = loadModel(JoinInfoModel.class, new JoinInfoParams(), conn, id);

        if (authModel != null && authModel.getPassword() != null) {
            player.setRegistered(true);
            player.setPassword(authModel.getPassword());
        }

        Log.$(player.getName() + " reg = " + player.getPassword());

        player.setPlayedLastTime(joinInfoModel.getLastOnline());
        player.setPlayedTotal(joinInfoModel.getOnlineTime());
    }

    private static <T extends Model> T loadModel(Class<T> fetchClass, ModelParams modelParams, Connection conn, int id) {
        return conn
                .createQuery(modelParams.selectSQL())
                .addParameter("id", id)
                .setColumnMappings(modelParams.getCols())
                .throwOnMappingFailure(false)
                .executeAndFetchFirst(fetchClass);

    }

}
