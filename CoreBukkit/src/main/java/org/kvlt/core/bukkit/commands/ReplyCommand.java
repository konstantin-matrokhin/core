package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.entity.GamerEntity;
import org.kvlt.core.bukkit.packets.ReplyPacket;

public class ReplyCommand implements CommandInterface {

    public ReplyCommand() {
        SpigotCommand command = getCommandsAPI()
                .register("r", this, "reply");
        command.setOnlyPlayers(true);
    }

    @Override
    public void execute(GamerEntity sender, String s, String[] args) {
        if (args.length < 1) return;

        String from = sender.getName();
        String message = String.join(" ", args);

        ReplyPacket replyPacket = new ReplyPacket(from, message);
        replyPacket.send();
    }
}
