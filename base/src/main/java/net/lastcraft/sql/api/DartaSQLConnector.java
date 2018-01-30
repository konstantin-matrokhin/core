package net.lastcraft.sql.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DartaSQLConnector implements SQLConnection {
    static List<SQLConnection> connections = new ArrayList<>();

    private Connection connection;
    private ExecutorService executor;
    private String host;
    private String user;
    private String password;
    private String database;

    DartaSQLConnector(String host, String user, String password, String database) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
        executor = Executors.newSingleThreadExecutor();
        connections.add(this);
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        return connection;
    }

    private void connect() throws SQLException {
        String sql = "jdbc:mysql://" +
                host + ":3306" +
                '/' +
                database +
                "?characterEncoding=cp1251" +
                "&autoReconnect=true&maxReconnects=10";
        connection = DriverManager.getConnection(sql, user, password);
    }

    @Override
    public void close() {
        if (connection == null)
            return;
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executor.shutdownNow();
        connections.remove(this);
    }

    @Override
    public void execute(String sql) {
        execute(sql, true);
    }

    @Override
    public void execute(String sql, boolean async) {
        Runnable runnable = () -> {
            try {
                Statement statement = getConnection().createStatement();
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
