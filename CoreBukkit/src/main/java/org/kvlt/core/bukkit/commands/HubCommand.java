package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kvlt.core.bukkit.packets.HubRequestPacket;

public class HubCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player p = (Player) sender;
        String name = p.getName();

        new HubRequestPacket(name).send();

//        ProxySwitchServerPacket pssp = new ProxySwitchServerPacket(new OnlinePlayer(p.getName()));
//        CorePlugin.get().getCoreServer().writeAndFlush(pssp);

        return true;
    }
}
