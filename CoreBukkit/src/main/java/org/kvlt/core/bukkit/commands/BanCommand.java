package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.entity.GamerEntity;
import net.lastcraft.group.Group;
import org.kvlt.core.bukkit.packets.BanPacket;
import org.kvlt.core.protocol.PacketUtil;

public class BanCommand implements CommandInterface {

    public BanCommand() {
        final SpigotCommand command = getCommandsAPI()
                .register("ban", this);
        command.setMinimalGroup(Group.MODERATOR);
    }

    @Override
    public void execute(GamerEntity gamerEntity, String s, String[] args) {
        if (args.length < 1) return;

        String enforcer = gamerEntity.getName();
        String victim = args[0];
        String time = (args.length > 1) ? args[1] : "e";
        String reason = (args.length > 2) ? args[2] : PacketUtil.EMPTY_STRING;

        new BanPacket(enforcer, victim, time, reason).send();
    }
}
