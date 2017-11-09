package org.kvlt.core.db;

/**
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


}
