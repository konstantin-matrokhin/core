package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.kvlt.core.bukkit.packets.BroadcastPacket;

import java.util.Arrays;

public class ServerAlertCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length < 2) return false;

        String senderName = sender.getName();
        String server = args[0];

        String words = String.join(" ",
                Arrays.copyOfRange(args, 1, args.length));
        new BroadcastPacket(senderName, words, server).send();

        return true;
    }
}
