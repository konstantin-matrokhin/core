package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.kvlt.core.bukkit.packets.BanPacket;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length != 3) return false;

        String victim = args[0];
        String time = args[1];
        String reason = args[2];

        new BanPacket(sender.getName(), victim, time, reason).send();

        return true;
    }

}
