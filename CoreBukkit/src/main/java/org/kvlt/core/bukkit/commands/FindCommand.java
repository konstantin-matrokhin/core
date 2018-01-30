package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.api.player.GamerEntity;
import net.lastcraft.group.Group;
import org.kvlt.core.bukkit.packets.PlayerInfoPacket;

public class FindCommand implements CommandInterface {

    public FindCommand() {
        SpigotCommand command = getCommandsAPI()
                .register("find", this);
        command.setMinimalGroup(Group.HELPER);
    }

    @Override
    public void execute(GamerEntity sender, String s, String[] args) {
        if (args.length == 0) return;

        String senderName = sender.getName();
        String playerName = args[0];

        new PlayerInfoPacket(senderName, playerName, false).send();
    }
}
