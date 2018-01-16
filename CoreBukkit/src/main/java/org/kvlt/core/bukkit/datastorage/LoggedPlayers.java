package org.kvlt.core.bukkit.datastorage;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class LoggedPlayers {

    private static List<String> loggedPlayers;

    static {
        loggedPlayers = new ArrayList<>();
    }

    public static void logIn(String playerName) {
        if (loggedPlayers.stream().noneMatch(p -> p.equalsIgnoreCase(playerName))) loggedPlayers.add(playerName);
    }

    public static void logOut(String playerName) {
        loggedPlayers.removeIf(p -> p.equalsIgnoreCase(playerName));
    }

    public static boolean isLogged(String playerName) {
        return loggedPlayers.stream().anyMatch(p -> p.equalsIgnoreCase(playerName));
    }

}
