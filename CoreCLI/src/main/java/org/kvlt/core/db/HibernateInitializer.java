package org.kvlt.core.db;


import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.kvlt.core.config.Config;
import org.kvlt.core.entities.ServerPlayer;

public class HibernateInitializer {

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

    public static void start() {
        String name = "core_startup";
        PlayerFactory.loadPlayer(name);
    }
}
