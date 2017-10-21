package org.kvlt.core.db;

import java.sql.ResultSet;

/**
 * Класс для доступа к БД.
 * TODO: перекрыть прямой доступ к запросам
 */
public class DB {

    private static MySQLConnection mySQLConnection = new MySQLConnection();
    private static PlayerDB playerDB = new PlayerDB();

    public static PlayerDB getPlayerDB() {
        return playerDB;
    }

    public static void query(String sql) {
        try {
            mySQLConnection.getConnection().createStatement().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet queryData(String sql) {
        try {
            return mySQLConnection.getConnection().createStatement().executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MySQLConnection getMySQLConnection() {
        return mySQLConnection;
    }

}
