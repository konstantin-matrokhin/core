package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.api.player.GamerEntity;
import net.lastcraft.group.Group;
import org.kvlt.core.bukkit.packets.BroadcastRequestPacket;

import java.util.Arrays;

public class ServerAlertCommand implements CommandInterface {

    public ServerAlertCommand() {
        SpigotCommand command = getCommandsAPI()
                .register("salert", this);
        command.setMinimalGroup(Group.MODERATOR);
    }

    @Override
    public void execute(GamerEntity sender, String s, String[] args) {
        if (args.length < 2) return;

        String senderName = sender.getName();
        String server = args[0];
        String words = String.join(" ",
                Arrays.copyOfRange(args, 1, args.length));

        new BroadcastRequestPacket(senderName, words, server).send();

    }
}
