package org.kvlt.core.bungee.storages;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;

public class IdMap {

    private static HashMap<String, Integer> idMap;

    static {
        idMap = new HashMap<>();
    }

    public static void setId(String player, int id) {
        idMap.put(player, id);
    }

    public static int getId(String player) {
        return idMap.get(player);
    }

    public static String getPlayer(int id) {
        return idMap.entrySet().stream()
                .filter(entry -> entry.getValue() == id)
                .findFirst()
                .orElse(null)
                .getKey();
    }

}
