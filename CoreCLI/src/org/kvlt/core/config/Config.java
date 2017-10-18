package org.kvlt.core.config;

import java.util.HashMap;

public class Config {

    private static final String FILE_NAME = "config.json";
    private static final String MYSQL_SECTION = "mysql";
    private static final String PROXY_SECTION = "proxy";
    private static final String SERVER_SECTION = "bukkit";

    private static ConfigManager configManager;
    private static HashMap<String, String> mysqlData;
    private static HashMap<String, String> proxyData;
    private static HashMap<String, String> bukkitData;

    public static void init() {
        configManager = new ConfigManager();
        configManager.init(FILE_NAME);

        try {
            mysqlData = configManager.loadSection(MYSQL_SECTION);
            proxyData = configManager.loadSection(PROXY_SECTION);
            bukkitData = configManager.loadSection(SERVER_SECTION);
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

    public static String getServer(String key) {
        return configManager.getValue(bukkitData, key);
    }

}
