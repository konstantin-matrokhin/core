package org.kvlt.core.bungee.auth;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.bungee.CoreDB;
import org.kvlt.core.bungee.storages.IdMap;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

public class Auth {

    private static String LOGIN_SQL = "SELECT * FROM authentication WHERE id = ?";
    private static String INFO_SQL = "SELECT * FROM join_info WHERE id = ?";

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

        if (ProxyLoggedPlayers.isLogged(player)) return true;

        ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(player);
        final String ip = pp.getAddress().getHostString();

        ResultSet authData = getAuthData(id);
        ResultSet infoData = getInfoData(id);

        try {
            if (authData != null && infoData != null) {
                dbPassword = authData.getString("password");
                dbIp = infoData.getString("ip");
            } else {
                pp.sendMessage("Вы не зарегистрированы!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("LOGGING IN..");
        if (dbPassword != null && dbIp != null) {
            if (password.equals(dbPassword)) {
                ProxyLoggedPlayers.logIn(player);
                return true;
            } else {
                pp.sendMessage("Неверный пароль!");
            }
        } else {
            pp.sendMessage("Вы не зарегистрированы! (2)");
        }

        return false;
    }

    public static boolean trySessionAuth(String player) {
        final int id = IdMap.getId(player);
        final long now = System.currentTimeMillis();

        ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(player);

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

        if (lastAuth != -1 && dbIp != null) {
            if (dbIp.equals(ip)) {
                long timeInterval = now - lastAuth;

                if (inSessionRange(timeInterval)) {
                    ProxyLoggedPlayers.logIn(player);
                    return true;
                } else {
                    pp.sendMessage("Вас не было слишком давно, введите пароль.");
                    pp.sendMessage("last auth = " + TimeUnit.MILLISECONDS.toHours(lastAuth) + ", delta = " + TimeUnit.MILLISECONDS.toHours(timeInterval));
                }
            } else {
                pp.sendMessage("IP не совпадают!");
            }
        } else {
            pp.sendMessage("Вы не зарегистрированы, поэтому сессия не прошла!");
        }
        return false;
    }

    public static boolean inSessionRange(long time) {
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        return hours < 24 && time > -1;
    }

}
