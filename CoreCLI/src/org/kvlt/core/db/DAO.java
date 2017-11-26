package org.kvlt.core.db;

import org.kvlt.core.config.Config;
import org.kvlt.core.utils.Log;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.concurrent.TimeUnit;

public class DAO {

    private static Sql2o sql;
    private static Connection c;

    public static void connect() {
        Log.$("Подключение к MySQL..");
        long conStart = System.currentTimeMillis();

        String host = Config.getMySQL("host");
        String port = Config.getMySQL("port");
        String db = Config.getMySQL("db");
        String dbUser = Config.getMySQL("user");
        String dbPassword = Config.getMySQL("password");

        String dbParams = "useUnicode=true&" +
                "useJDBCCompliantTimezoneShift=true&" +
                "useLegacyDatetimeCode=false&" +
                "serverTimezone=UTC&" +
                "autoReconnect=true";

        String dbUrl = String.format("jdbc:mysql://%s:%s/%s", host, port, db)
                .concat("?")
                .concat(dbParams);

        sql = new Sql2o(dbUrl, dbUser, dbPassword);
        c = sql.open();

        long conTime = System.currentTimeMillis() - conStart;
        String totalTime = String.format("%d", TimeUnit.MILLISECONDS.toSeconds(conTime));

        Log.$("Подключено к MySQL (" + db + ") за " + totalTime + " сек.");
    }

    public static Connection getConnection() {
        return c;
    }

}
