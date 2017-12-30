package org.kvlt.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    private static SimpleDateFormat simpleDateFormat;

    static {
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public static void $(String str) {
        $(LogType.INFO, str);
    }

    public static void err(String str) {
        $(LogType.ERROR, str);
    }

    public static void warn(String str) {
        $(LogType.WARN, str);
    }

    public static void $(LogType level, String str) {
        String time = simpleDateFormat.format(new Date());
        System.out.println("[" + time + "][" + level + "] " + str);
    }

    public enum LogType {
        ERROR, WARN, INFO
    }


}
