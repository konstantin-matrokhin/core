package org.kvlt.core.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class ConfigManager {

    public static FileConfiguration config() {
        return CorePlugin.get().getConfig();
    }

    public static void initConfig() {
        CorePlugin.get().saveDefaultConfig();
    }

    public static String getClientName() {
        String path = new File(Bukkit.getWorldContainer().getAbsolutePath()).getParentFile().getName();
        return path;
    }

}
