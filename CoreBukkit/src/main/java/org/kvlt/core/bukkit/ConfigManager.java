package org.kvlt.core.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class ConfigManager {

    public static FileConfiguration config() {
        return CorePlugin.getPlugin().getConfig();
    }

    public static void initConfig() {
        CorePlugin.getPlugin().saveDefaultConfig();
    }

    public static String getClientName() {
        return new File(Bukkit.getWorldContainer().getAbsolutePath()).getParentFile().getName();
    }

}
