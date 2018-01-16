package org.kvlt.core.db;

import io.netty.util.internal.ConcurrentSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.kvlt.core.entities.Group;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.utils.Log;

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
        sessionFactory = HibernateInitializer.getSessionFactory();
    }

    public static void addTask(Runnable r) {
        Future f = executor.submit(r);
        queries.add(f);
    }

    public static Collection<Future<?>> queue() {
        return queries;
    }

    public static ServerPlayer loadPlayer(String name) {
        return loadPlayer(name, true);
    }

    public static ServerPlayer loadPlayer(String name, boolean create) {
        ServerPlayer player;
        Session s = sessionFactory.openSession();

        s.beginTransaction();
        Query query = s.createQuery("FROM ServerPlayer WHERE name = :name");
        query.setParameter("name", name);

        player = (ServerPlayer) query.uniqueResult();

        s.getTransaction().commit();

        if (create) {
            if (player == null) {
                s.beginTransaction();
                player = new ServerPlayer(name);
                s.save(player);
                s.getTransaction().commit();
            }
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

    public static void ban(ServerPlayer player, String enforcer, String until, String reason) {
        player.setBanned(true);
        player.setBannedUntil(PlayedTimeCounter.parseTime(until));
        player.setBannedBy(enforcer);
        player.setBanReason(reason);
        player.setBanAmount(player.getBanAmount() + 1);
        player.kick(reason);
        updatePlayer(player);

        Log.$(String.format("%s забанен: %s", player.getName(), reason));
    }

    public static boolean isStaff(ServerPlayer player) {
        return Group.getGroup(player.getGroup()).getLevel() >= Group.JUNIOR.getLevel();
    }

    public static void unban(ServerPlayer player) {
        player.setBanned(false);
        player.setBannedUntil(0);
        player.setBanReason(null);
        player.setBannedBy(null);
        updatePlayer(player);
    }
}
