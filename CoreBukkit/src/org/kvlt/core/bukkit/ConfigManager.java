package org.kvlt.core.bukkit;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    public static FileConfiguration config() {
        return CorePlugin.get().getConfig();
    }

    public static void initConfig() {
        CorePlugin.get().saveDefaultConfig();
    }

    public static String getClientName() {
        return config().getString("client-name");
    }

}
