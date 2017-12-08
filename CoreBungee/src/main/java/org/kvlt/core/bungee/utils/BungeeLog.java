package org.kvlt.core.bungee.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;

public class BungeeLog {

    private static String PREFIX = ChatColor.AQUA + "[CORE]";

    public static void $(String str) {
        ProxyServer.getInstance().getLogger().info(PREFIX + " " + str);
    }

}
