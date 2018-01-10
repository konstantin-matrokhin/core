package org.kvlt.core.bungee.storages;

import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.packets.PremiumPlayerPacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class PremiumQueue {

    private static int DEFAULT_DURATION = 5;

    private static Map<String, Long> premiumPlayers;

    static {
        premiumPlayers = new ConcurrentHashMap<>();
    }

    public static void add(String name) {
        long now = System.currentTimeMillis();
        if (!CoreBungee.get().getPremiumPlayers().contains(name)) {
            premiumPlayers.put(name, now + TimeUnit.MINUTES.toMillis(DEFAULT_DURATION));
        }
    }

    public static boolean has(String name) {
        if (premiumPlayers.containsKey(name)) {
            long time = premiumPlayers.get(name);
            long now = System.currentTimeMillis();
            if (time > now) {
                return true;
            } else {
                System.out.println(time);
                System.out.println(now);
                remove(name);
            }
        }
        return false;
    }

    public static void setPremium(String name) {
        new PremiumPlayerPacket(name).send();
        remove(name);
    }

    public static void remove(String name) {
        premiumPlayers.remove(name);
    }

}
