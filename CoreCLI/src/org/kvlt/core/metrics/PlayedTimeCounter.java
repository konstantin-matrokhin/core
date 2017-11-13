package org.kvlt.core.metrics;

import org.kvlt.core.entities.OnlinePlayer;

public class PlayedTimeCounter {

    public static void start(OnlinePlayer op) {
        op.setJoinTime(System.currentTimeMillis());
    }

    public static long stop(OnlinePlayer op) {
        op.setLeaveTime(System.currentTimeMillis());
        return op.getLeaveTime() - op.getJoinTime();
    }

}
