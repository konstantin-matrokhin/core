package org.kvlt.core.db;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.PlayerModel;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.models.*;
import org.sql2o.Connection;

import java.math.BigInteger;

/**
 * Для управления записями игрокамов в БД
 */
public class PlayerDB {

/*    @Deprecated
    public static int initId(String playerName) {
        String sql1 = "INSERT INTO identifier (player_name) VALUES (:name)\n" +
                "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id), player_name = :name;";

        String sql2 = "SELECT LAST_INSERT_ID() AS id;";

        return DAO.getConnection()
                .createQuery(sql1)
                .addParameter("name", playerName)
                .executeUpdate()
                .createQuery(sql2)
                .executeAndFetchFirst(Integer.class);
    }*/

    public static void save(OnlinePlayer player) {
        int id = player.getId();

        long playedNow = PlayedTimeCounter.stop(player);

        String sql1 = "UPDATE join_info SET online_time = online_time + :now WHERE id = :id";
        String sql2 = "UPDATE join_info SET last_online = :now WHERE  id = :id";

        DAO.getConnection()
                .createQuery(sql1)
                .addParameter("id", id)
                .addParameter("now", playedNow)
                .executeUpdate()
                .createQuery(sql2)
                .withParams()
                .addParameter("id", id)
                .addParameter("now", playedNow)
                .executeUpdate();
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

        int id = loadId(player.getName());

        if (id == 0) {
            createPlayerModel(player);
        } else {
            player.setId(id);
            loadPlayerModel(player);
        }

        PlayedTimeCounter.start(player);
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
            DAO.getConnection().createQuery(q).addParameter("id", id).executeUpdate();
        }

        player.setId(id);

    }

    private static void loadPlayerModel(ServerPlayer player) {
        int id = player.getId();
        Connection conn = DAO.getConnection();

        AuthModel authModel = loadModel(AuthModel.class, new AuthParams(), conn, id);
        JoinInfoModel joinInfoModel = loadModel(JoinInfoModel.class, new JoinInfoParams(), conn, id);

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

    public static ServerPlayer loadServerPlayer(String name) {
        ServerPlayer player = new PlayerModel(name);
        int id = loadId(name);
        player.setId(id);
        loadPlayerModel(player);
        return player;
    }

}
