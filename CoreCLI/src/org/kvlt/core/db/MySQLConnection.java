package org.kvlt.core.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {

    private Connection connection;



    public MySQLConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.co
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
