package org.kvlt.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    public static void $(String str) {
        $(LogType.INFO, str);
    }

    public static void $(LogType level, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = simpleDateFormat.format(new Date());
        System.out.println("[" + level + "][" + time + "] " + str);
    }

}
