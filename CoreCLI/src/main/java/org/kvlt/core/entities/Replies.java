package org.kvlt.core.entities;

import java.util.HashMap;

public class Replies {

    public static final HashMap<ServerPlayer, ServerPlayer> replyMap;

    static {
        replyMap = new HashMap<>();
    }

    public static void setCompanion(ServerPlayer forWhom, ServerPlayer companion) {
        replyMap.put(forWhom, companion);
    }

    public static void resetCompanion(ServerPlayer player) {
        replyMap.remove(player);
    }

    public static ServerPlayer getCompanion(ServerPlayer player) {
        return replyMap.get(player);
    }

    public static boolean hasCompanion(ServerPlayer player) {
        return replyMap.get(player) != null;
    }

}
