package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.kvlt.core.bukkit.packets.BroadcastPacket;

public class AlertCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) return false;

        String senderName = sender.getName();
        String words = String.join(" ", args);
        new BroadcastPacket(senderName, words).send();

        return true;
    }
}
