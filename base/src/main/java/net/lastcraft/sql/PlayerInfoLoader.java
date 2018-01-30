package net.lastcraft.sql;

import net.lastcraft.sql.api.LastCraftSQL;
import net.lastcraft.sql.api.SQLConnection;
import net.lastcraft.sql.api.table.ColumnType;
import net.lastcraft.sql.api.table.TableColumn;
import net.lastcraft.sql.api.table.TableConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerInfoLoader {
    private static SQLConnection connection;

    static {
        connection = LastCraftSQL.createConnect("s3" + Connection.domane, "root", Connection.password, "lobby");
        new TableConstructor("lobby_data",
                new TableColumn("id", ColumnType.INT_11).primaryKey(true),
                new TableColumn("keys", ColumnType.INT),
                new TableColumn("exp", ColumnType.INT)
        ).create(connection);
    }

    public static void close() {
        try {
            connection.close();
        } catch (Exception ignored) { }
    }

    public static int[] getData(int playerID) {
        int keys = -1;
        int exp = -1;
        try {
            //todo сделать иначе  (в скайпе чекнуть)
            PreparedStatement statement = connection.getConnection()
                    .prepareStatement("SELECT * FROM `lobby_data` WHERE `id` = ? LIMIT 1;");
            statement.setInt(1, playerID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                keys = rs.getInt("keys");
                exp  = rs.getInt("exp");
            } else {
                keys = 1;
                exp = 0;
                connection.execute("INSERT INTO `lobby_data` (`id`, `exp`, `keys`) VALUES ('" + playerID + "','" + exp + "', '" + keys + "');");
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return new int[] {keys, exp};
    }

    public static void updateData(int playerID, String column, int data) {
        connection.execute("UPDATE `lobby_data` SET `" + column + "`=`" + column + "` + '" + data + "' WHERE `id`=" + playerID + ";");
    }
}
