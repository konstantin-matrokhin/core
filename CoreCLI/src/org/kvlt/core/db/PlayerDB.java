package org.kvlt.core.db;

import org.kvlt.core.entities.ServerPlayer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Для управления записями игрокамов в БД
 */
public class PlayerDB {

    private static final String PLAYER_TABLE = "players";
    private static final String NAME_COLUMN = "name";
    private static final String UUID_COLUMN = "uuid";

    private Connection connection;

    {
        connection = DB.getMySQLConnection().getConnection();
    }

    //TODO: Replace with prepared statements!!
    public void addPlayer(ServerPlayer p) throws Exception {
        String getPlayerQuery = String.format(
                "SELECT id FROM %s WHERE %s=?",
                PLAYER_TABLE,
                NAME_COLUMN);
        PreparedStatement ps = connection.prepareStatement(getPlayerQuery);
        ps.setString(1, p.getName());
        ResultSet idResult = ps.executeQuery();

        int id;
        if (idResult.next()) {
            id = idResult.getInt("id");
        } else {
            id = -1;
        }

        String addPlayerQuery = String.format(
                "INSERT INTO %s (%s, %s) VALUES (?, ?) ON DUPLICATE KEY UPDATE ID = ?",
                PLAYER_TABLE,
                NAME_COLUMN,
                UUID_COLUMN);
        PreparedStatement psAdd = connection.prepareStatement(addPlayerQuery);
        psAdd.setString(1, p.getName());
        psAdd.setString(2, p.getUUID().toString());
        psAdd.setInt(3, id);

        p.setId(id);

        if (id < 1) {
            DB.query(addPlayerQuery);
        }
    }

    public void setPlayerBan(boolean toBan, ServerPlayer p, String by, Date time, String reason) {
        int inc = toBan ? 1 : 0;
        try {
            String banSQL = String.format("UPDATE %s SET\n" +
                    "is_banned=?," +
                    "banned_by=?," +
                    "ban_amount=ban_amount + ?," +
                    "banned_until=?" +
                    "ban_reason=? " +
                    "WHERE name=?",

                    PLAYER_TABLE);

            PreparedStatement ps = connection.prepareStatement(banSQL);
            ps.setBoolean(1, toBan);
            ps.setString(2, by);
            ps.setInt(3, inc);
            ps.setDate(4, time);
            ps.setString(5, reason);
            ps.setString(6, p.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
