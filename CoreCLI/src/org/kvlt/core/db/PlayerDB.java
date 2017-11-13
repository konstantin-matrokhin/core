package org.kvlt.core.db;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.utils.Log;
import org.sql2o.Query;

import java.math.BigInteger;

/**
 * Для управления записями игрокамов в БД
 */
public class PlayerDB {

    private static final String PLAYER_TABLE = "players";
    private static final String NAME_COLUMN = "name";
    private static final String UUID_COLUMN = "uuid";

    @Deprecated
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
    }

    public static void save(OnlinePlayer player) {
        int id = player.getId();
        long playedNow = player.getLeaveTime() - player.getJoinTime();

        String sql1 = "UPDATE join_info SET online_time = online_time + " + playedNow + " WHERE id = " + id;
        String sql2 = "UPDATE join_info SET last_online = " + playedNow + " WHERE  id = " + id;

        DAO.getConnection()
                .createQuery(sql1)
                .executeUpdate()
                .createQuery(sql2)
                .executeUpdate();
    }

    public static void loadOnlinePlayer(OnlinePlayer player) {
        int id;
        String existSql = "SELECT IF(COUNT(id) = 0, 0, id) AS id FROM identifier WHERE player_name = :name";

        id = DAO.getConnection()
                .createQuery(existSql)
                .addParameter("name", player.getName())
                .executeScalar(Integer.class);

        if (id == 0) {
            createPlayerModel(player);
        } else {
            loadPlayerModel(player);
        }
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

    private static void loadPlayerModel(OnlinePlayer player) {

    }

}
