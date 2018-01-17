package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.kvlt.core.bukkit.packets.PlayerInfoPacket;

public class FindCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length != 1) return false;
        String senderName = sender.getName();
        String playerName = args[0];

        new PlayerInfoPacket(senderName, playerName, false).send();

        return true;
    }

}
