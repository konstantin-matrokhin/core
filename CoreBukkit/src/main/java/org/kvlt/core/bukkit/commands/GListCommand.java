package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.kvlt.core.bukkit.packets.GListPacket;

public class GListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String pattern = "n";

        if (args.length == 1) {
            pattern = args[0];
        }

        String senderName = sender.getName();
        new GListPacket(senderName, pattern).send();
        return true;
    }
}
