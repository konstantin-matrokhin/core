package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.entity.GamerEntity;
import net.lastcraft.group.Group;
import org.bukkit.entity.Player;
import org.kvlt.core.bukkit.packets.TransferRequestPacket;

public class ServerCommand implements CommandInterface {

    public ServerCommand() {
        SpigotCommand command = getCommandsAPI()
                .register("server", this);
        command.setMinimalGroup(Group.MODERATOR);
    }

    @Override
    public void execute(GamerEntity sender, String s, String[] args) {
        if (!(sender instanceof Player)) return;
        if (args.length != 1) return;

        Player p = (Player) sender;
        String to = args[0];

        new TransferRequestPacket(p.getName(), to).send();
    }
}
