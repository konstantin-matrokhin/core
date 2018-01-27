package org.kvlt.core.utils;

import java.util.logging.Logger;

public class Log {

//    private static SimpleDateFormat simpleDateFormat;
    private static final Logger logger;

    static {
        logger = Logger.getLogger("CoreCLI");
//        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    public static void $(String str) {
        logger.info(str);
    }

    public static void err(String str) {
        logger.severe(str);
    }

    public static void warn(String str) {
        logger.warning(str);
    }

}
