package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.kvlt.core.packets.bukkit.BroadcastPacket;

import java.util.Arrays;

public class ServerAlertCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length < 2) return false;

        String server = args[0];
        String senderName = commandSender.getName();
        String[] msgArr = Arrays.copyOfRange(args, 1, args.length);
        String msg = String.join(" ", msgArr);

        BroadcastPacket bp = new BroadcastPacket(msg, server, senderName);

        return false;
    }
}
