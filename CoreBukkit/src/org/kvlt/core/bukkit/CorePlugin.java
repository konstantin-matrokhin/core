package org.kvlt.core.bukkit;

import io.netty.channel.Channel;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.kvlt.core.bukkit.commands.*;
import org.kvlt.core.bukkit.events.PlayerBasicEventListener;
import org.kvlt.core.bukkit.net.ConnectionManager;

public class CorePlugin extends JavaPlugin {

    private static CorePlugin instance;
    private Channel server;

    @Override
    public void onEnable() {

        synchronized (this) {
            instance = this;
        }

        Bukkit.getPluginManager().registerEvents(new PlayerBasicEventListener(), this);

        getCommand("broadcast").setExecutor(new PingCommand());
        getCommand("msg").setExecutor(new MsgCommand());
        getCommand("time").setExecutor(new TimeCommand());
        getCommand("hub").setExecutor(new HubCommand());
        getCommand("sendcommand").setExecutor(new SendCommandCommand());

        ConfigManager.initConfig();
        ConnectionManager.get().startClient();
    }

    @Override
    public void onDisable() {
        ConnectionManager.get().disconnect();
    }

    public Channel getCoreServer() {
        return server;
    }

    public void setServer(Channel ch) {
        server = ch;
    }

    public static synchronized CorePlugin get() {
        return instance;
    }

}

