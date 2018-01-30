package org.kvlt.core.bukkit;

import org.bukkit.plugin.java.JavaPlugin;
import org.kvlt.core.bukkit.commands.*;
import org.kvlt.core.bukkit.utils.BukkitLocale;

public class CorePlugin extends JavaPlugin {

    private static CorePlugin instance;
    private static CoreAPI api;

    {
    synchronized (this) {
            instance = this;
        }
    }

    @Override
    public void onEnable() {
        api = new MainCoreBukkit();

        BukkitLocale.load();

        new AlertCommand();
        new ServerAlertCommand();
        new MsgCommand();
        new TimeCommand();
        new HubCommand();
        new SendCommand();
        new ReplyCommand();
        new BanCommand();
        new ServerCommand();
        new FindCommand();
        new GListCommand();
        new WhoisCommand();

        ConfigManager.initConfig();
        api.connect();
    }

    @Override
    public void onDisable() {
        api.disconnect();
    }

    public static CoreAPI getAPI() {
        return api;
    }

    public static JavaPlugin getPlugin() {
        return instance;
    }

}

