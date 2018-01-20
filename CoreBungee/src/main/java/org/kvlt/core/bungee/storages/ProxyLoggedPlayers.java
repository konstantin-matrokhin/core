package org.kvlt.core.bungee.storages;

import io.netty.util.internal.ConcurrentSet;

import java.util.Set;

public class ProxyLoggedPlayers {

    private static Set<String> players;

    static {
        players = new ConcurrentSet<>();
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
