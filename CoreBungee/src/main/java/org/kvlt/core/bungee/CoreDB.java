package org.kvlt.core.bungee;

import org.kvlt.core.bungee.utils.BungeeLog;

import java.sql.Connection;
import java.sql.DriverManager;

public class CoreDB {

    private static CoreDB instance;

    private Connection connection;
    private String host;
    private String port;
    private String username;
    private String password;
    private String db;

    public void connect(String host, int port, String username, String password, String db) {
        BungeeLog.$("Соединяюсь с базой данных..");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = String.format("jdbc:mysql://%s:%d/%s", host, port, db);
            connection = DriverManager.getConnection(url, username, password);
            BungeeLog.$("Подключено!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized CoreDB get() {
        return instance == null ? instance = new CoreDB() : instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
