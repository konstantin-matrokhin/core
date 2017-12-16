package org.kvlt.core.bungee.storages;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class ProxyLoggedPlayers {

    private static List<String> players;

    static {
        players = new ArrayList<>();
    }

    public static void logIn(String player) {
        players.add(player);
    }

    public static void logOut(String player) {
        players.remove(player);
    }

    public static boolean isLogged(String player) {
        return players.contains(player);
    }

}
