package org.kvlt.core.bukkit.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.logging.Level;

public class Log {

    private static String prefix = "&b[CORE]&r";

    public static void $(Object data) {
        Bukkit.getLogger().log(Level.OFF,
                ChatColor.translateAlternateColorCodes(
                        '&',
                        prefix + " " + data
                )
        );
    }

}
