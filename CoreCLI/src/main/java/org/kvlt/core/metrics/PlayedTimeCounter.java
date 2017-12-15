package org.kvlt.core.metrics;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.ServerPlayer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Отвечает за время игры игрока
 */
public class PlayedTimeCounter {

    public static final SimpleDateFormat sdf;

    static {
        sdf = new SimpleDateFormat("mm:ss");
    }

    public static boolean inSessionRange(long time) {
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        return hours < 24 && time > -1;
    }

    /**
     * Фиксирует время входа
     * @param op игрок для которого нужно зафиксировать
     */
    public static void start(ServerPlayer op) {
        op.setJoinTime(System.currentTimeMillis());
    }

    /**
     * Фиксирует время выхода
     * @param op игрок
     * @return возвращает разницу во времени в LONG
     */
    public static long stop(ServerPlayer op) {
        op.setLeaveTime(System.currentTimeMillis());
        return op.getLeaveTime() - op.getJoinTime();
    }

    /**
     * Форматирует время в понятный для человека вид
     * @param time
     * @return
     */
    public static String getFormatedTime(long time) {
        Date date = new Date(time);
        return sdf.format(date);
    }
}
