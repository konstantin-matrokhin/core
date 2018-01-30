package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.player.GamerEntity;
import net.lastcraft.group.Group;
import org.kvlt.core.bukkit.packets.GListPacket;
import org.kvlt.core.protocol.PacketUtil;

public class GListCommand implements CommandInterface {

    public GListCommand() {
        getCommandsAPI()
                .register("glist", this, "online")
                .setMinimalGroup(Group.ADMIN);
    }

    @Override
    public void execute(GamerEntity sender, String s, String[] args) {
        String pattern = PacketUtil.EMPTY_STRING;

        if (args.length == 1) {
            pattern = args[0];
        }

        String senderName = sender.getName();
        new GListPacket(senderName, pattern).send();
    }
}
