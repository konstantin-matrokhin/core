package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kvlt.core.bukkit.packets.ReplyPacket;

public class ReplyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player) || args.length < 1) return false;
        Player p = (Player) sender;

        String from = p.getName();
        String message = String.join(" ", args);

        ReplyPacket replyPacket = new ReplyPacket(from, message);
        replyPacket.send();

        return true;
    }

}
