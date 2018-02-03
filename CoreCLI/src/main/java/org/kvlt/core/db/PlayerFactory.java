package org.kvlt.core.db;

import io.netty.util.internal.ConcurrentSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.kvlt.core.Core;
import org.kvlt.core.entities.Group;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.packets.player.IpBanPacket;
import org.kvlt.core.packets.player.IpBanRequestPacket;
import org.kvlt.core.utils.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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

    public static ServerPlayer getPlayer(String player) {
        ServerPlayer onlinePlayer = Core.get().getOnlinePlayer(player);
        if (onlinePlayer == null) {
            return loadPlayer(player, false);
        } else {
            return onlinePlayer;
        }
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

    public static String getPrettyInfo(String name) {
        ServerPlayer player;
        String info;

        boolean online = Core.get().getOnlinePlayers().contains(name);
        if (online) {
            player = Core.get().getOnlinePlayers().get(name);
        } else {
            player = PlayerFactory.loadPlayer(name, false);
        }

        if (player != null) {
            List<String> i = new ArrayList<>(Arrays.asList(
                    "==========================",
                    "ID: " + player.getId(),
                    "Онлайн: " + online,
                    "Последний IP: " + player.getLastIp(),
                    "Забанен: " + player.isBanned(),
                    "В муте: " + player.isMuted(),
                    "Банов: " + player.getBanAmount(),
                    "Мутов: " + player.getMuteAmount(),
                    "Репортов: " + player.getTotalReports(),
                    "Последний сервер: " + player.getLastServer(),
                    "Почта: " + player.getEmail(),
                    "Последний вход: " + player.getLastAuth() // TODO normalize
            ));

            if (player.isBanned()) {
                i.add("==========================");
                i.add("Кто забанил: " + player.getBannedBy());
                i.add("Причина бана: " + player.getBanReason());
                i.add("Конец бана: " + player.getBannedUntil());
                i.add("==========================");
            }

            if (player.isMuted()) {
                i.add("==========================");
                i.add("Кто замутил: " + player.getMutedBy());
                i.add("Причина мута: " + player.getMuteReason());
                i.add("Конец мута: " + player.getMutedUntil());
                i.add("==========================");
            }

            if (online) {
                i.add("==========================");
                i.add("IP: " + player.getIp());
                i.add("Сейчас на сервере: " + player.getCurrentServer());
                i.add("Сейчас на прокси: " + player.getCurrentProxy());
            }
            i.add("==========================");
            info = String.join("\n", i);
        } else {
            info = "Игрока нет в БД!";
        }

        return info;
    }

    public static String getShortInfo(String name) {
        ServerPlayer player;
        List<String> i = new ArrayList<>();

        boolean online = Core.get().getOnlinePlayers().contains(name);
        i.add("Онлайн: " + online);

        if (online) {
            player = Core.get().getOnlinePlayers().get(name);
            i.add("Сервер: " + player.getCurrentServer());
            i.add("Прокси: " + player.getCurrentProxy());
        } else {
            player = PlayerFactory.loadPlayer(name, false);
            if (player != null) {
                i.add("Последний сервер: " + player.getLastServer());
            } else {
                i.add("Игрока нет в БД!");
            }
        }

        return String.join("\n", i);
    }

    public static void banIp(String ip) {
        Core.get().getProxies().send(new IpBanPacket(ip));
        Log.warn(String.format("IP %s has been banned!", ip));
    }

}
