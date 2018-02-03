package org.kvlt.core.bungee.storages;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class IpBanlist {

    private static final long DEFAULT_BAN_TIME = TimeUnit.MINUTES.toMillis(15);
    private static Map<String, Long> banlist = new HashMap<>();

    public static long ban(String ip) {
        long until = System.currentTimeMillis() + DEFAULT_BAN_TIME;
        banlist.put(ip, until);
        return until;
    }

    public static void unban(String ip) {
        banlist.remove(ip);
    }

    public static boolean isBanned(String ip) {
        if (banlist.containsKey(ip)) {
            long until = banlist.get(ip);
            if (until > System.currentTimeMillis()) {
                return true;
            } else {
                banlist.remove(ip);
            }
        }
        return false;
    }

}
