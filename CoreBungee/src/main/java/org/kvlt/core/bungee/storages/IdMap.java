package org.kvlt.core.bungee.storages;

import java.util.HashMap;
import java.util.Objects;

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
        return Objects.requireNonNull(idMap.entrySet().stream()
                .filter(entry -> entry.getValue() == id)
                .findFirst()
                .orElse(null))
                .getKey();
    }

}
