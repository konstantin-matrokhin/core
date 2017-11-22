package org.kvlt.core.bukkit.datastorage;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LoggedPlayers {

    private static List<Player> loggedPlayers;

    static {
        loggedPlayers = new ArrayList<>();
    }

    public static void logIn(Player p) {
        loggedPlayers.add(p);
    }

    public static void logOut(Player p) {
        loggedPlayers.remove(p);
    }

    public static boolean isLogged(Player p) {
        return loggedPlayers.contains(p);
    }

}
