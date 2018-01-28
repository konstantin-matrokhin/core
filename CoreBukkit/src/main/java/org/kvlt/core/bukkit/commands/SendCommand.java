package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.entity.GamerEntity;
import net.lastcraft.group.Group;
import org.kvlt.core.bukkit.packets.TransferRequestPacket;

public class SendCommand implements CommandInterface {

    public SendCommand() {
        SpigotCommand command = getCommandsAPI()
                .register("send", this, "transfer");
        command.setMinimalGroup(Group.MODERATOR);
    }

    @Override
    public void execute(GamerEntity sender, String s, String[] args) {
        if (args.length != 2) return;

        String who = args[0];
        String where = args[1];

        new TransferRequestPacket(who, where).send();
    }
}
