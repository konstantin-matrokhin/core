package org.kvlt.core.bukkit;

import io.netty.channel.Channel;
import org.bukkit.plugin.java.JavaPlugin;
import org.kvlt.core.bukkit.commands.*;
import org.kvlt.core.bukkit.net.ConnectionManager;
import org.kvlt.core.protocol.PacketResolver;

public class CorePlugin extends JavaPlugin {

    private static CorePlugin instance;
    private Channel server;
    private PacketResolver packetResolver;

    {
    synchronized (this) {
            instance = this;
        }
    }

    //TODO: API | transfer, change language packets

    @Override
    public void onEnable() {
        packetResolver = new PacketResolver();

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

    public PacketResolver getPacketResolver() {
        return packetResolver;
    }
}

