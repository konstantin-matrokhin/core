package org.kvlt.core.db;

import org.kvlt.core.entities.ServerPlayer;

import java.sql.ResultSet;

/**
 * Для управления записями игрокамов в БД
 */
public class PlayerDB {

    private static final String PLAYER_TABLE = "players";
    private static final String NAME_COLUMN = "name";
    private static final String UUID_COLUMN = "uuid";

    //TODO: Replace with prepared statements!!
    public void addPlayer(ServerPlayer p) throws Exception {
        String getPlayerQuery = String.format(
                "SELECT id FROM %s WHERE %s='%s'",
                PLAYER_TABLE,
                NAME_COLUMN,
                p.getName());

        ResultSet idResult = DB.queryData(getPlayerQuery);

        int id;
        if (idResult.next()) {
            id = idResult.getInt("id");
        } else {
            id = -1;
        }

        String addPlayerQuery = String.format(
                "INSERT INTO %s (%s, %s) VALUES ('%s', '%s') ON DUPLICATE KEY UPDATE ID = %s",
                PLAYER_TABLE,
                NAME_COLUMN,
                UUID_COLUMN,
                p.getName(),
                p.getUUID().toString(),
                id);

        if (id < 1) {
            DB.query(addPlayerQuery);
        }
    }

}
