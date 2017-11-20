package org.kvlt.core.metrics;

import org.kvlt.core.entities.OnlinePlayer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayedTimeCounter {

    public static void start(OnlinePlayer op) {
        op.setJoinTime(System.currentTimeMillis());
    }

    public static long stop(OnlinePlayer op) {
        op.setLeaveTime(System.currentTimeMillis());
        return op.getLeaveTime() - op.getJoinTime();
    }

    public static String getFormatedTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("k:mm:ss");
        Date date = new Date(time);
        return sdf.format(date);
    }
}
