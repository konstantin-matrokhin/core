package org.kvlt.core.bukkit;

import io.netty.channel.Channel;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.kvlt.core.bukkit.commands.MsgCommand;
import org.kvlt.core.bukkit.commands.PingCommand;
import org.kvlt.core.bukkit.events.EventManager;
import org.kvlt.core.bukkit.net.ConnectionManager;
import org.kvlt.core.packets.bukkit.BroadcastPacket;
import org.kvlt.core.packets.player.PlayerMessagePacket;

public class CorePlugin extends JavaPlugin {

    private static CorePlugin instance;
    private Channel server;

    @Override
    public void onEnable() {

        synchronized (this) {
            instance = this;
        }

        EventManager.get().start();

        getCommand("ping").setExecutor(new PingCommand());
        getCommand("msg").setExecutor(new MsgCommand());

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

