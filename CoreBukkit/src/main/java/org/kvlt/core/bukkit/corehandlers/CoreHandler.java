package org.kvlt.core.bukkit.corehandlers;

import org.bukkit.Bukkit;

public class CoreHandler {

    public static void executeServerCommand(String cmd) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
    }

}
