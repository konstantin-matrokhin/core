package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.kvlt.core.bukkit.ConfigManager;
import org.kvlt.core.bukkit.net.ConnectionManager;
import org.kvlt.core.packets.bukkit.BroadcastPacket;

public class AlertCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) return false;
        BroadcastPacket bc = new BroadcastPacket(args[0], sender.getName());
        ConnectionManager.get().getChannel().writeAndFlush(bc);
        return true;
    }
}
