package org.kvlt.core.bungee.auth;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.CoreDB;
import org.kvlt.core.bungee.storages.IdMap;
import org.kvlt.core.bungee.storages.PremiumQueue;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;
import org.kvlt.core.entities.PremiumPlayers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Auth {

    private static final String LOGIN_SQL = "SELECT * FROM authentication WHERE id = ?";
    private static final String INFO_SQL = "SELECT * FROM join_info WHERE id = ?";

    private static Connection mysqlConnection;

    static {
        mysqlConnection = CoreDB.get().getConnection();
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

    public static boolean passwordAuth(String player, String password) {
        final int id = IdMap.getId(player);

        String dbIp = null;
        String dbPassword = null;

        boolean alreadyLogged = CoreBungee.get().getPremiumPlayers().contains(player) ||
                ProxyLoggedPlayers.isLogged(player);

        if (alreadyLogged) {
            ProxyLoggedPlayers.logIn(player);
            return true;
        }

        ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(player);
        final String ip = pp.getAddress().getHostString();

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
                return true;
            } else {
                response = "Неверный пароль!";
            }
        } else {
            response = "Вы не зарегистрированы!";
        }

        pp.sendMessage(new TextComponent(response));

        return false;
    }

    public static boolean trySessionAuth(String player) {
        ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(player);

        if (CoreBungee.get().getPremiumPlayers().contains(player)) {
            ProxyLoggedPlayers.logIn(player);
            pp.sendMessage(new TextComponent("Дороу!"));
            return true;
        }

        final int id = IdMap.getId(player);
        final long now = System.currentTimeMillis();

        final String ip = pp.getAddress().getHostString();

        String dbIp = null;
        long lastAuth = 0;

        if (ProxyLoggedPlayers.isLogged(player)) return true;

        ResultSet authData = getAuthData(id);
        ResultSet infoData = getInfoData(id);

        try {
            lastAuth = authData.getLong("last_authenticated");
            dbIp = infoData.getString("ip");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String response;
        if (lastAuth != -1) {
            if (Objects.equals(ip, dbIp)) {
                long timeInterval = now - lastAuth;

                if (inSessionRange(timeInterval)) {
                    ProxyLoggedPlayers.logIn(player);
                    pp.sendMessage(new TextComponent("С возвращением!"));
                    return true;
                } else {
                    response = "Рады видеть тебя снова! Авторизуйся, пожалуйста. Не попал в интервал";
                }
            } else {
                response = "Рады видеть тебя снова! Авторизуйся, пожалуйста. Ип разный";
            }
        } else {
            response = "Добро пожаловать! Зарегистрируйтесь, пожалуйста. /register <пароль> <повтор_пароля>";
        }

        pp.sendMessage(new TextComponent(response));
        return false;
    }

    public static boolean inSessionRange(long time) {
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        return hours < 24 && time > -1;
    }

}
