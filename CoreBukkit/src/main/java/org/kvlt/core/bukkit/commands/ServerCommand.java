package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kvlt.core.bukkit.packets.TransferRequestPacket;

public class ServerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;

        String to = args[0];

        if (to != null) {
            new TransferRequestPacket(p.getName(), to).send();
            return true;
        }

        return false;
    }

}
