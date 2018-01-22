package org.kvlt.core.config;

import com.google.gson.JsonElement;

import java.util.HashMap;

/**
 * Класс главного конфига, дающий доступ к <b>чтению</b> его полей
 */
public final class Config {

    private static final String FILE_NAME = "config.json";
    private static final String MYSQL_SECTION = "mysql";
    private static final String PROXY_SECTION = "proxy";
    private static final String SMTP_SECTION = "smtp";
    private static final String CORE_SECTION = "core";

    private static ConfigManager configManager;
    private static HashMap<String, JsonElement> mysqlData;
    private static HashMap<String, JsonElement> proxyData;
    private static HashMap<String, JsonElement> smtpData;
    private static HashMap<String, JsonElement> coreData;

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
        return configManager.getValue(mysqlData, key).getAsJsonPrimitive().getAsString();
    }

    public static String getProxy(String key) {
        return configManager.getValue(proxyData, key).getAsJsonPrimitive().getAsString();
    }

    public static String getSMTP(String key) {
        return configManager.getValue(smtpData, key).getAsJsonPrimitive().getAsString();
    }

    public static JsonElement getCore(String key) {
        return configManager.getValue(coreData, key);
    }

}
