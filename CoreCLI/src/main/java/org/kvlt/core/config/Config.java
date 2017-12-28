package org.kvlt.core.config;

import java.util.HashMap;

/**
 * Класс главного конфига, дающий доступ к <b>чтению</b> его полей
 */
public class Config {

    private static final String FILE_NAME = "config.json";
    private static final String MYSQL_SECTION = "mysql";
    private static final String PROXY_SECTION = "proxy";
    private static final String SMTP_SECTION = "smtp";
    private static final String CORE_SECTION = "core";

    private static ConfigManager configManager;
    private static HashMap<String, String> mysqlData;
    private static HashMap<String, String> proxyData;
    private static HashMap<String, String> smtpData;
    private static HashMap<String, String> coreData;

    public static void init() {
        configManager = new ConfigManager();
        configManager.init(FILE_NAME);

        try {
            mysqlData = configManager.loadSection(MYSQL_SECTION);
            proxyData = configManager.loadSection(PROXY_SECTION);
            coreData = configManager.loadSection(CORE_SECTION);
            smtpData = configManager.loadSection(SMTP_SECTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getMySQL(String key) {
        return configManager.getValue(mysqlData, key);
    }

    public static String getProxy(String key) {
        return configManager.getValue(proxyData, key);
    }

    public static String getSMTP(String key) {
        return configManager.getValue(smtpData, key);
    }

    public static String getCore(String key) {
        return configManager.getValue(coreData, key);
    }

}
