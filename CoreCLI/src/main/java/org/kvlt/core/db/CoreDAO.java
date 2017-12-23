package org.kvlt.core.db;

import org.kvlt.core.config.Config;
import org.kvlt.core.utils.Log;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Deprecated
public class CoreDAO {

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
            Log.warn("Всё нормально, без паники!");

            List<String> sqlLines = sqlListResource("db_create.sql");

            if (sqlLines == null) {
                Log.err("Исходный SQL-файл не найден.");
                System.exit(0);
            }
            Log.warn("Это может занять какое-то время");

            sqlLines.forEach(line -> {
                try {
                    if (!line.trim().isEmpty() && !line.startsWith("--")) {
                        c.createQuery(line).executeUpdate();
                    }
                } catch (Exception e) {
                    System.out.println("exc @ " + line);
                }
            });

        } else {
            Log.$("База загружена.");
        }

    }

    private static List<String> sqlListResource(String fileName) {
        //InputStream is = ResourceLoader.newResourceStream(fileName);
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("db_create.sql")))    {
            list = reader.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try (InputStream is = new FileInputStream(new File("db_create.sql"))) {
//            scanner = new Scanner(is).useDelimiter("\\A");
//            str = scanner.hasNext() ? scanner.next() : "";
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            scanner.close();
//        }

        return list;
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
