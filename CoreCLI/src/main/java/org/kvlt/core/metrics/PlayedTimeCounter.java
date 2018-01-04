package org.kvlt.core.metrics;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.entities.ServerPlayer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Отвечает за время игры игрока
 */
public class PlayedTimeCounter {

    public static final SimpleDateFormat sdf;

    private static final Map<Character, TimeUnit> timeMap;

    static {
        sdf = new SimpleDateFormat("mm:ss");

        timeMap = new HashMap<Character, TimeUnit>() {{
            put('h', TimeUnit.HOURS);
            put('m', TimeUnit.MINUTES);
            put('d', TimeUnit.DAYS);
        }};
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

    public static long parseTime(String time) {
        long currentTime = System.currentTimeMillis();
        char lastChar = time.charAt(time.length() - 1);

        if (lastChar == 'e') return -1;

        long timePart = Long.valueOf(time.substring(0, time.length() - 2));
        for (Map.Entry timeEntry: timeMap.entrySet()) {
            if (lastChar == (char) timeEntry.getKey()) {
                TimeUnit timeUnit = (TimeUnit) timeEntry.getValue();
                return currentTime + timeUnit.toMillis(timePart);
            }
        }
        return 0;
    }
}
