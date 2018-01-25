package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
//        Player p;
//        if (commandSender instanceof Player) {
//            p = (Player) commandSender;
//        } else {
//            return false;
//        }
//
//        PlayerTimePacket ptp = new PlayerTimePacket(p.getName());
//        CorePlugin.get().getServer().writeAndFlush(ptp);

        return true;
    }
}
