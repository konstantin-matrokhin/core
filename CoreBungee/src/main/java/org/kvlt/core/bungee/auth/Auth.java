package org.kvlt.core.bungee.auth;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.bungee.CoreDB;

public class Auth {

    private static String LOGIN_SQL = "SELECT * FROM authentication WHERE id = ?";

    public static boolean tryAuth(ProxiedPlayer player, String password) {
        String name = player.getName();
        String ip = player.getAddress().getHostString();
        long now = System.currentTimeMillis();

        CoreDB.get().getConnection().createStatement().execute("");

        return false;
    }

}
