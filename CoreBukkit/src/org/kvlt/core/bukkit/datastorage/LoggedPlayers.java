package org.kvlt.core.bukkit.datastorage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LoggedPlayers {

    private static List<Player> loggedPlayers;

    static {
        loggedPlayers = new ArrayList<>();
    }

    public static void logIn(String playerName) {
        Player p = Bukkit.getPlayer(playerName);
        loggedPlayers.add(p);
    }

    public static void logOut(String playerName) {
        Player p = Bukkit.getPlayer(playerName);
        loggedPlayers.remove(p);
    }

    public static boolean isLogged(String playerName) {
        Player p = Bukkit.getPlayer(playerName);
        return loggedPlayers.contains(p);
    }

}
