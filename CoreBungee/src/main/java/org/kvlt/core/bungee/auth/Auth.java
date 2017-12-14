package org.kvlt.core.bungee.auth;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.bungee.CoreDB;
import org.kvlt.core.bungee.storages.IdMap;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

        Connection con = CoreDB.get().getConnection();

        try {
            PreparedStatement authQuery = con.prepareStatement(LOGIN_SQL);
            authQuery.setInt(1, id);
            ResultSet authResult = authQuery.executeQuery();

            PreparedStatement infoQuery = con.prepareStatement(INFO_SQL);
            authQuery.setInt(1, id);
            ResultSet infoResult = authQuery.executeQuery();

            authResult.next();
            infoResult.next();

            dbPassword = authResult.getString("password");
            dbIp = infoResult.getString("ip");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbPassword != null && dbIp != null) {

        }

        return false;
    }

}
