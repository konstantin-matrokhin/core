package org.kvlt.core.bukkit;

import org.bukkit.configuration.file.FileConfiguration;

import static org.kvlt.core.bukkit.CorePlugin.*;

public class ConfigManager {

    public static FileConfiguration config() {
        return get().getConfig();
    }

    public static void initConfig() {
        get().saveDefaultConfig();
    }

    public static String getClientName() {
        return config().getString("client-name");
    }

}
