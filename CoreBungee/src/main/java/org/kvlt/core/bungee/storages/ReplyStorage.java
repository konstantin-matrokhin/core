package org.kvlt.core.bungee.storages;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;

public class ReplyStorage {

    private static HashMap<ProxiedPlayer, ProxiedPlayer> lastInterlocutor;

    static {
        lastInterlocutor = new HashMap<>();
    }

    public static void setLastInterlocutor(String to, String who) {
        ProxiedPlayer toPlayer = ProxyServer.getInstance().getPlayer(to);
        ProxiedPlayer whoPlayer = ProxyServer.getInstance().getPlayer(who);

        if (toPlayer != null && whoPlayer != null) {
            lastInterlocutor.put(toPlayer, whoPlayer);
        }
    }

    public static ProxiedPlayer getLastInterlocutor(String to) {
        ProxiedPlayer toPlayer = ProxyServer.getInstance().getPlayer(to);

        if (lastInterlocutor.get(toPlayer) != null) {
            return lastInterlocutor.get(toPlayer);
        }
        return null;
    }

    public static boolean hasLastInterlocutor(String to) {
        ProxiedPlayer toPlayer = ProxyServer.getInstance().getPlayer(to);

        if (toPlayer != null && lastInterlocutor.get(toPlayer) != null) {
            return true;
        }
        return false;
    }

}
