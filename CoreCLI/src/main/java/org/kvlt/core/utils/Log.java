package org.kvlt.core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

//    private static SimpleDateFormat simpleDateFormat;
    private static final Logger logger;

    static {
        logger = LogManager.getLogger(Log.class);
//        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public static void $(String str) {
        logger.info(str);
    }

    public static void err(String str) {
        logger.error(str);
    }

    public static void warn(String str) {
        logger.warn(str);
    }

}
