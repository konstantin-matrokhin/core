package org.kvlt.core.bungee.auth;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.CoreDB;
import org.kvlt.core.bungee.storages.IdMap;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Auth {

    private static final String LOGIN_SQL = "SELECT * FROM authentication WHERE id = ?";
    private static final String INFO_SQL = "SELECT * FROM join_info WHERE id = ?";

    private static Connection mysqlConnection;
    private static Map<ProxiedPlayer, ScheduledTask> annoyingMessages;

    static {
        mysqlConnection = CoreDB.get().getConnection();
        annoyingMessages = new HashMap<>();
    }

    private static ResultSet getInfoData(int id) {
        try {
            PreparedStatement infoQuery = mysqlConnection.prepareStatement(INFO_SQL);
            infoQuery.setInt(1, id);
            ResultSet infoResult = infoQuery.executeQuery();
            infoResult.next();

            return infoResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ResultSet getAuthData(int id) {
        try {
            PreparedStatement authQuery = mysqlConnection.prepareStatement(LOGIN_SQL);
            authQuery.setInt(1, id);
            ResultSet authResult = authQuery.executeQuery();
            authResult.next();

            return authResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void passwordAuth(String player, String password) {
        final int id = IdMap.getId(player);
        boolean alreadyLogged = CoreBungee.get().getPremiumPlayers().contains(player) ||
                ProxyLoggedPlayers.isLogged(player);

        if (alreadyLogged) {
            ProxyLoggedPlayers.logIn(player);
            return;
        }

        final ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(player);
        final String ip = pp.getAddress().getHostString();

        ProxyServer.getInstance().getScheduler().runAsync(CoreBungee.get(), () -> {
            String dbIp = null;
            String dbPassword = null;

            ResultSet authData = getAuthData(id);
            ResultSet infoData = getInfoData(id);

            try {
                if (authData != null && infoData != null) {
                    dbPassword = authData.getString("password");
                    dbIp = infoData.getString("ip");
                } else {
                    pp.sendMessage(new TextComponent("Вы не зарегистрированы!"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String response;
            if (dbPassword != null) {
                if (password.equals(dbPassword)) {
                    ProxyLoggedPlayers.logIn(player);
                    pp.sendMessage(new TextComponent("С возвращением!"));
                    return;
                } else {
                    response = "Неверный пароль!";
                }
            } else {
                response = "Вы не зарегистрированы!";
            }
            pp.sendMessage(new TextComponent(response));
        });
    }

    public static void trySessionAuth(String player) {
        ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(player);

        if (ProxyLoggedPlayers.isLogged(player)) return;

        if (CoreBungee.get().getPremiumPlayers().contains(player)) {
            ProxyLoggedPlayers.logIn(player);
            pp.sendMessage(new TextComponent("Дороу!"));
            return;
        }

        final int id = IdMap.getId(player);
        final long now = System.currentTimeMillis();
        final String ip = pp.getAddress().getHostString();

        ProxyServer.getInstance().getScheduler().runAsync(CoreBungee.get(), () -> {
            String dbIp = null;
            long lastAuth = 0;

            ResultSet authData = getAuthData(id);
            ResultSet infoData = getInfoData(id);

            try {
                lastAuth = authData.getLong("last_authenticated");
                dbIp = infoData.getString("ip");
            } catch (Exception e) {
                e.printStackTrace();
            }

            TextComponent response = new TextComponent();
            if (lastAuth != -1) {
                if (Objects.equals(ip, dbIp)) {
                    long timeInterval = now - lastAuth;

                    if (inSessionRange(timeInterval)) {
                        ProxyLoggedPlayers.logIn(player);
                        pp.sendMessage(new TextComponent("С возвращением!"));
                        return;
                    } else {
                        response.setText("Рады видеть тебя снова! Авторизуйся, пожалуйста. /login <пароль>");
                    }
                } else {
                    response.setText("Рады видеть тебя снова! Авторизуйся, пожалуйста. /login <пароль>");
                }
            } else {
                response.setText("Добро пожаловать! Зарегистрируйтесь, пожалуйста. /register <пароль> <повтор_пароля>");
            }
            Runnable annoyingTask = () -> {
                pp.sendMessage(response);
            };

            ScheduledTask task = ProxyServer.getInstance()
                    .getScheduler().schedule(CoreBungee.get(), annoyingTask,
                            1L, 2L, TimeUnit.SECONDS);
            annoyingMessages.put(pp, task);
        });
    }

    public static boolean inSessionRange(long time) {
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        return hours < 24 && time > -1;
    }

    public static Map<ProxiedPlayer, ScheduledTask> getAnnoyingMessages() {
        return annoyingMessages;
    }
}
