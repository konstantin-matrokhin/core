package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.entity.GamerEntity;
import net.lastcraft.group.Group;
import org.kvlt.core.bukkit.packets.PlayerInfoPacket;

public class WhoisCommand implements CommandInterface {

    public WhoisCommand() {
        SpigotCommand command = getCommandsAPI()
                .register("whois", this);
        command.setMinimalGroup(Group.MODERATOR);
    }

    @Override
    public void execute(GamerEntity sender, String s, String[] args) {
        if (args.length != 1) return;

        String senderName = sender.getName();
        String playerName = args[0];

        new PlayerInfoPacket(senderName, playerName, true).send();
    }
}
