package org.kvlt.core.bungee.utils;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;

public class BungeeLog {

    private static String PREFIX = ChatColor.AQUA + "[CORE]";

    public static void $(String str) {
        BungeeCord.getInstance().getLogger().info(PREFIX + " " + str);
    }

}
