package org.kvlt.core.bukkit;

import io.netty.channel.Channel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.kvlt.core.bukkit.events.EventManager;
import org.kvlt.core.bukkit.net.ConnectionManager;

public class CorePlugin extends JavaPlugin {

    private static CorePlugin instance;
    private Channel server;

    @Override
    public void onEnable() {

        synchronized (this) {
            instance = this;
        }

        EventManager.get().start();

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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ping")) {

            return true;
        }
        return false;
    }

    public static synchronized CorePlugin get() {
        return instance;
    }

}

