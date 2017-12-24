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

    private static final ExecutorService executor;
    private static final Collection<Future<?>> queries;

    private static final SessionFactory sessionFactory;

    static {
        queries = new ConcurrentSet<>();
        executor = Executors.newSingleThreadExecutor();
        sessionFactory = HibernateInitiaizer.getSessionFactory();
    }

    public static void addTask(Runnable r) {
        Future f = executor.submit(r);
        queries.add(f);
    }

    public static Collection<Future<?>> queue() {
        return queries;
    }

    public static ServerPlayer loadPlayer(String name) {
        ServerPlayer player;
        Session s = sessionFactory.openSession();

        s.beginTransaction();
        Query query = s.createQuery("FROM ServerPlayer WHERE name = :name");
        query.setParameter("name", name);

        player = (ServerPlayer) query.uniqueResult();

        s.getTransaction().commit();

        if (player == null) {
            s.beginTransaction();
            player = new ServerPlayer(name);
            s.save(player);
            s.getTransaction().commit();
        }
        s.close();

        return player;
    }

    public static void updatePlayer(ServerPlayer player) {
        updatePlayer(player, true);
    }

    public static void updatePlayer(ServerPlayer player, boolean async) {
        Runnable r = () -> {
            Session regSession = sessionFactory.openSession();
            regSession.beginTransaction();
            regSession.update(player);
            regSession.getTransaction().commit();
            regSession.close();
        };

        if (async) {
            addTask(r);
        } else {
            r.run();
        }
    }

    public static void register(ServerPlayer player, String password) {
        player.setPassword(password);
        player.setRegisterIp(player.getIp());
        player.setLastAuth(System.currentTimeMillis());
        updatePlayer(player);
    }
}
