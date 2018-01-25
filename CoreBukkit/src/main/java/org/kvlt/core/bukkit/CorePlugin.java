package org.kvlt.core.bukkit;

import io.netty.channel.Channel;
import org.bukkit.plugin.java.JavaPlugin;
import org.kvlt.core.bukkit.commands.*;
import org.kvlt.core.protocol.PacketResolver;

public class CorePlugin extends JavaPlugin {

    private static CorePlugin instance;
    private static CoreAPI api;

    private Channel server;
    private PacketResolver packetResolver;

    {
    synchronized (this) {
            instance = this;
        }
    }

    @Override
    public void onEnable() {
        api = new MainCoreBukkit(this);

        getCommand("alert").setExecutor(new AlertCommand());
        getCommand("salert").setExecutor(new ServerAlertCommand());
        getCommand("msg").setExecutor(new MsgCommand());
        getCommand("time").setExecutor(new TimeCommand());

        new HubCommand();

        getCommand("sendcommand").setExecutor(new SendCommand());
        getCommand("reply").setExecutor(new ReplyCommand());
        getCommand("con").setExecutor(new ConnectCommand());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("server").setExecutor(new ServerCommand());
        getCommand("find").setExecutor(new FindCommand());
        getCommand("glist").setExecutor(new GListCommand());
        getCommand("whois").setExecutor(new WhoisCommand());

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

