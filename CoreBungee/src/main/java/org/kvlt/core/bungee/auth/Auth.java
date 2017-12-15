package org.kvlt.core.bungee.auth;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
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

    public static boolean tryAuth(ProxiedPlayer player, String password) {
        int id = IdMap.getId(player);
        long now = System.currentTimeMillis();
        String name = player.getName();
        String ip = player.getAddress().getHostString();
        String dbIp = null;
        String dbPassword = null;
        long lastAuth = 0;


        if (ProxyLoggedPlayers.isLogged(name)) return true;

        Connection con = CoreDB.get().getConnection();

        try {
            PreparedStatement authQuery = con.prepareStatement(LOGIN_SQL);
            authQuery.setInt(1, id);
            ResultSet authResult = authQuery.executeQuery();

            PreparedStatement infoQuery = con.prepareStatement(INFO_SQL);
            infoQuery.setInt(1, id);
            ResultSet infoResult = authQuery.executeQuery();

            authResult.next();
            infoResult.next();

            dbPassword = authResult.getString("password");
            lastAuth = authResult.getLong("last_authenticated");

            dbIp = infoResult.getString("ip");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbPassword != null && dbIp != null) {
            long loginInterval = now - lastAuth;

            if (inSessionRange(loginInterval)) {
                if (dbIp.equals(ip)) {
                    ProxyLoggedPlayers.logIn(name);
                    return true;
                }
            } else {
                if (password.equals(dbPassword)) {
                    ProxyLoggedPlayers.logIn(name);
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean inSessionRange(long time) {
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        return hours < 24 && time > -1;
    }

}
