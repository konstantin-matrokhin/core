package net.enot.dartasystem.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Енот on 30.04.2017.
 */
public class SQLConnection {

    private Connection connection;
    private ExecutorService executor;
    private String host;
    private String database;
    private String user;
    private String password;
    public static List<SQLConnection> connections = new ArrayList<>();

    public SQLConnection(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.executor = Executors.newSingleThreadExecutor();
        connections.add(this);
    }

    public SQLConnection(String host, String user, String password, String database) {
        this(host, user, password);
        this.database = database;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            this.connect();
        }
        return connection;
    }

    public void close() throws SQLException {
        connections.remove(this);
        connection.close();
        executor.shutdownNow();
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection("JDBC:mysql://" + host + ":3306/" + database + "?autoReconnect=true", user, password);
    }

    public void execute(String sql) {
        this.execute(sql, true);
    }

    public void execute(String sql, boolean async) {
        Runnable runnable = () -> {
            try {
                Statement statement = this.getConnection().createStatement();
                Throwable throwable = null;
                try {
                    statement.executeUpdate(sql);
                } catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                } finally {
                    if (statement != null) {
                        if (throwable != null) {
                            try {
                                statement.close();
                            } catch (Throwable throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                        } else {
                            statement.close();
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };
        if (async) {
            executor.execute(runnable);
        } else {
            runnable.run();
        }
    }
}
