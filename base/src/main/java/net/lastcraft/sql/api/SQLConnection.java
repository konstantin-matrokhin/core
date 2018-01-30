package net.lastcraft.sql.api;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLConnection {

    /**
     * выполнить запрос асинк
     * @param sql - запрос
     */
    void execute(String sql);

    void execute(String sql, boolean async);

    /**
     * Получить соединение до базы
     * @return соединение для работы
     */
    Connection getConnection() throws SQLException;

    /**
     * Закрыть соединение
     */
    void close();
}
