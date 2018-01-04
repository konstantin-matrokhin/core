package org.kvlt.core.bungee.storages;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Infractions {

    private static Map<String, Long> mutes;

    static {
        mutes = new HashMap<>();
    }

    public static void mute(String playerName, long until) {
        mutes.put(playerName, until);
    }

    public static void unmute(String playerName) {
        mutes.remove(playerName);
    }

    public static boolean isMuted(String playerName) {
        long bannedUntil = Optional.ofNullable(mutes.get(playerName)).orElse(-1L);
        if (bannedUntil > System.currentTimeMillis()) {
            mutes.remove(playerName);
            return true;
        }
        return false;
    }

}
