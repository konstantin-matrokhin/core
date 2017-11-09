package org.kvlt.core.db;

import org.kvlt.core.config.Config;
import org.kvlt.core.utils.Log;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

/**
 * Создает соединение с БД с данными из конфига
 */
public class MySQLConnection {

    private Connection connection;

    public void connect() {

        Log.$("Подключение к MySQL..");

        String host = Config.getMySQL("host");
        String port = Config.getMySQL("port");
        String db = Config.getMySQL("db");
        String dbUser = Config.getMySQL("user");
        String dbPassword = Config.getMySQL("password");

        String dbParams = "useUnicode=true&" +
                "useJDBCCompliantTimezoneShift=true&" +
                "useLegacyDatetimeCode=false&" +
                "serverTimezone=UTC";

        String dbUrl = String.format("jdbc:mysql://%s:%s/%s", host, port, db)
                .concat("?")
                .concat(dbParams);

        Sql2o sql = new Sql2o(dbUrl, dbUser, dbPassword);
        connection = sql.open();
        Log.$("Подключено к MySQL базе " + db);
    }

    //TODO адекватно переписать
    public Connection getConnection() {
        try {
            if (connection == null || connection.getJdbcConnection().isClosed()) {
                Log.$("Соединение с БД отстуствует, переподключаюсь");
                connect();
            }
        } catch (Exception e) {
            try {
                wait(3000);
            } catch (Exception we) {}
            return getConnection();
        }
        return connection;
    }

}
