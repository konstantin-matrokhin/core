package net.lastcraft.sql.api;

import java.util.List;

public class LastCraftSQL {

    public static SQLConnection createConnect(String host, String name, String password) {
        return new DartaSQLConnector(host, name, password, "");
    }

    public static SQLConnection createConnect(String host, String name, String password, String database) {
        return new DartaSQLConnector(host, name, password, database);
    }

    public static List<SQLConnection> getConnection() {
        return DartaSQLConnector.connections;
    }
}
