package org.kvlt.core.db;

import org.kvlt.core.entities.OnlinePlayer; /**
 * Для управления записями игрокамов в БД
 */
public class PlayerDB {

    private static final String PLAYER_TABLE = "players";
    private static final String NAME_COLUMN = "name";
    private static final String UUID_COLUMN = "uuid";

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
        player.setJoinTime(System.currentTimeMillis());

        String existSql = "SELECT id FROM identifier WHERE id = " + player.getId();
        int id = DAO.getConnection().createQuery(existSql).executeScalar(Integer.class);
        if (id < 1) {
            createPlayerModel(player);
        } else {
            loadPlayerModel(player);
        }
    }

    private static void createPlayerModel(OnlinePlayer player) {

    }

    private static void loadPlayerModel(OnlinePlayer player) {

    }

}
