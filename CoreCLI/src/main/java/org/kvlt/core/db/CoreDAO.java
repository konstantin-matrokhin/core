package org.kvlt.core.db;

import org.kvlt.core.ResourceLoader;
import org.kvlt.core.config.Config;
import org.kvlt.core.utils.Log;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLNonTransientConnectionException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CoreDAO {

    //TODO: change to chech if exists
    private static final String CHECK_IF_VALID_QUERY = "SELECT 1 FROM identifier LIMIT 1";

    private static Sql2o sql;
    private static Connection c;

    public static void start() {
        connect();

        int isValid;

        try {
            isValid = c.createQuery(CHECK_IF_VALID_QUERY).executeScalar(Integer.class);
        } catch (Exception e) {
            isValid = 0;
        }

        if (isValid < 1) {
            Log.warn("База повреждена или отсутствует, создание таблиц..");
            String fullSqlString = sqlFromResource("db_create.sql");
            if (fullSqlString == null) {
                Log.err("Исходный SQL-файл не найден.");
                System.exit(0);
            }
            Log.warn("Это может занять какое-то время");
            c.createQuery(fullSqlString).executeUpdate();
            Log.$("Готово.");
        } else {
            Log.$("База загружена.");
        }

    }

    private static String sqlFromResource(String fileName) {
        InputStream is = ResourceLoader.newResourceStream(fileName);
        String str = null;

        try (Scanner scanner = new Scanner(is).useDelimiter("\\A")) {
            str = scanner.hasNext() ? scanner.next() : "";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

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
