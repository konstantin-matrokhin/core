package org.kvlt.core.bukkit;

import io.netty.channel.Channel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.kvlt.core.bukkit.events.EventManager;
import org.kvlt.core.bukkit.net.ConnectionManager;
import org.kvlt.core.packets.BroadcastPacket;
import org.kvlt.core.packets.PlayerMessagePacket;

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
            if (args.length == 0) return false;
            BroadcastPacket bc = new BroadcastPacket(args[0], ConfigManager.getClientName(), sender.getName());
            ConnectionManager.get().getChannel().writeAndFlush(bc);
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("tell")) {
            if (args.length != 2) return false;
            PlayerMessagePacket pmp = new PlayerMessagePacket(
                    ConfigManager.getClientName(),
                    sender.getName(),
                    args[0],
                    args[1]);
            ConnectionManager.get().getChannel().writeAndFlush(pmp);
        }
        return false;
    }

    public static synchronized CorePlugin get() {
        return instance;
    }

}

