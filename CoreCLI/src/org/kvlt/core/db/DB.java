package org.kvlt.core.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

    private static DB instance;
    private MySQLConnection mySQLConnection;

    private DB() {
        mySQLConnection = new MySQLConnection();
    }

    public void querySend(String sql) throws SQLException {
        mySQLConnection.getConnection().createStatement().execute(sql);
    }

    public ResultSet queryData(String sql) throws SQLException {
        return mySQLConnection.getConnection().createStatement().executeQuery(sql);
    }

    public static synchronized DB get() {
        return instance == null ? instance = new DB() : instance;
    }

    public MySQLConnection getMySQLConnection() {
        return mySQLConnection;
    }

}
