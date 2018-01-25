package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.entity.GamerEntity;
import org.kvlt.core.bukkit.packets.HubRequestPacket;

public class HubCommand implements CommandInterface {

    private SpigotCommand command;

    public HubCommand() {
        command = getCommandsAPI().register("hub", this, "lobby");
        command.setOnlyPlayers(true);
    }

    @Override
    public void execute(GamerEntity gamer, String s, String[] args) {
        String name = gamer.getName();
        new HubRequestPacket(name).send();
    }

}
