package org.kvlt.core.db;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.kvlt.core.config.Config;
import org.kvlt.core.entities.ServerPlayer;

public final class HibernateInitializer {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = buildSessionFactory();
    }

    private static SessionFactory buildSessionFactory() {
        return new Configuration()
                .addPackage("com.concretepage.persistence")
                .setProperty("hibernate.connection.driver_class", Config.getMySQL("driver"))
                .setProperty("hibernate.connection.url", Config.getMySQL("url"))
                .setProperty("hibernate.connection.password", Config.getMySQL("password"))
                .setProperty("hibernate.connection.username", Config.getMySQL("user"))
                .setProperty("hibernate.dialect", Config.getMySQL("dialect"))
                .setProperty("show_sql", Config.getMySQL("show_sql"))
                .setProperty("format_sql", Config.getMySQL("format_sql"))
                .setProperty("hibernate.hbm2ddl.auto", Config.getMySQL("mode"))
                .addAnnotatedClass(ServerPlayer.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void init() {
        String name = "core_startup";
        PlayerFactory.loadPlayer(name);
    }
}
