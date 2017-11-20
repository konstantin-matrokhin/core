package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.packets.player.PlayerTimePacket;

public class TimeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p;
        if (commandSender instanceof Player) {
            p = (Player) commandSender;
        } else {
            return false;
        }

        PlayerTimePacket ptp = new PlayerTimePacket(p.getName());
        CorePlugin.get().getCoreServer().writeAndFlush(ptp);

        return true;
    }
}
