package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p;
        if (commandSender instanceof Player) {
            p = (Player) commandSender;
        } else {
            return false;
        }

//        ProxySwitchServerPacket pssp = new ProxySwitchServerPacket(new OnlinePlayer(p.getName()));
//        CorePlugin.get().getCoreServer().writeAndFlush(pssp);

        return true;
    }
}
