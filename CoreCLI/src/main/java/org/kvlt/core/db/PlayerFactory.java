package org.kvlt.core.db;

import io.netty.util.internal.ConcurrentSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.kvlt.core.entities.ServerPlayer;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PlayerFactory {

    public static final ExecutorService executor;
    public static final Collection<Future<?>> queries;

    private static final SessionFactory sessionFactory;

    static {
        queries = new ConcurrentSet<>();
        executor = Executors.newSingleThreadExecutor();
        sessionFactory = HibernateInitiaizer.getSessionFactory();
    }

    public static ServerPlayer loadPlayer(String name) {
        ServerPlayer player;
        Session s = sessionFactory.openSession();

        s.beginTransaction();
        Query query = s.createQuery("FROM identifier WHERE player_name = :name");
        query.setParameter("name", name);
        player = (ServerPlayer) query.uniqueResult();
        s.getTransaction().commit();
        s.close();

        return player;
    }

}
