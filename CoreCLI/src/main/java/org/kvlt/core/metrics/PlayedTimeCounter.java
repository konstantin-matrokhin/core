package org.kvlt.core.metrics;

import org.kvlt.core.entities.OnlinePlayer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Отвечает за время игры игрока
 */
public class PlayedTimeCounter {

    public static final SimpleDateFormat sdf;

    static {
        sdf = new SimpleDateFormat("mm:ss");
    }

    /**
     * Фиксирует время входа
     * @param op игрок для которого нужно зафиксировать
     */
    public static void start(OnlinePlayer op) {
        op.setJoinTime(System.currentTimeMillis());
    }

    /**
     * Фиксирует время выхода
     * @param op игрок
     * @return возвращает разницу во времени в LONG
     */
    public static long stop(OnlinePlayer op) {
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
