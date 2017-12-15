package org.kvlt.core.db;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.models.*;
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

        String sql = "UPDATE join_info SET\n" +
                "online_time = online_time + :now,\n" +
                "last_online = :now,\n" +
                "ip = :ip,\n" +
                "server = :server\n" +
                "WHERE id = :id";

        Runnable r = () -> {
            CoreDAO.getConnection()
                    .createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("now", playedNow)
                    .addParameter("ip", player.getIp())
                    .addParameter("server", player.getCurrentServer().getName())
                    .executeUpdate();
        };
        executor.submit(r);
    }

    public static void register(ServerPlayer player, String password) {
        Runnable r = () -> {
            int id = player.getId();
            String ip = "127.0.0.1"; // TODO : FIX !!!
            long now = System.currentTimeMillis();

            player.setPassword(password);
            player.setRegistered(true);

            String registerSql = "UPDATE authentication SET\n" +
                    "password = :pass,\n" +
                    "registration_ip = :ip,\n" +
                    "last_authenticated = :now\n" +
                    "WHERE id = :id";

            CoreDAO.getConnection()
                    .createQuery(registerSql)
                    .addParameter("pass", password)
                    .addParameter("ip", ip)
                    .addParameter("now", now)
                    .addParameter("id", id)
                    .executeUpdate();
        };

        executor.submit(r);
    }

    public static boolean correctPassword(ServerPlayer op, String password) {
        return op.getPassword() != null && op.getPassword().equals(password);
    }

    public static int loadId(String name) {
        int id;
        String existSql = "SELECT IF(COUNT(id) = 0, 0, id) AS id FROM identifier WHERE player_name = :name";

        id = CoreDAO.getConnection()
                .createQuery(existSql)
                .addParameter("name", name)
                .executeScalar(Integer.class);
        return id;
    }

    public static void loadPlayer(ServerPlayer player) {
        Runnable r = () -> {
            int id = loadId(player.getName());

            if (id == 0) {
                createPlayerModel(player);
            } else {
                player.setId(id);
                loadPlayerModel(player);
            }
        };

        executor.submit(r);
    }

    public static ServerPlayer loadServerPlayer(String name) {
        ServerPlayer player = new ServerPlayer(name);
        int id = loadId(name);

        player.setId(id);
        loadPlayerModel(player);

        return player;
    }

    private static void createPlayerModel(ServerPlayer player) {
        String playerName = player.getName();
        String insertIdSql = "INSERT INTO identifier (player_name) VALUES (:name) ON DUPLICATE KEY UPDATE id=id";

        BigInteger bigId = (BigInteger) CoreDAO.getConnection()
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
            executor.submit(() -> {
                CoreDAO.getConnection().createQuery(q).addParameter("id", id).executeUpdate();
            });
        }

        player.setId(id);

    }

    private static void loadPlayerModel(ServerPlayer player) {
        if (player == null) return;

        int id = player.getId();
        Connection conn = CoreDAO.getConnection();

        AuthModel authModel = loadModel(AuthModel.class, new AuthParams(), conn, id);
        JoinInfoModel joinInfoModel = loadModel(JoinInfoModel.class, new JoinInfoParams(), conn, id);
        InfractionsModel infractionsModel = loadModel(InfractionsModel.class, new InfractionsParams(), conn, id);

        if (authModel != null && authModel.getPassword() != null) {
            player.setRegistered(true);
            player.setPassword(authModel.getPassword());
        }

        player.setPlayedLastTime(joinInfoModel.getLastOnline());
        player.setPlayedTotal(joinInfoModel.getOnlineTime());

        //todo
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
