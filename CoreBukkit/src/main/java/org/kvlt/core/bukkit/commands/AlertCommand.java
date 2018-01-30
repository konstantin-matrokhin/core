package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.api.player.GamerEntity;
import org.kvlt.core.bukkit.packets.BroadcastRequestPacket;

public class AlertCommand implements CommandInterface {

    public AlertCommand() {
        SpigotCommand command = getCommandsAPI()
                .register("alert", this);
    }

    @Override
    public void execute(GamerEntity sender, String s, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("/alert <message>");
            return;
        }

        String name = sender.getName();
        String words = String.join(" ", args);

        new BroadcastRequestPacket(name, words).send();
    }
}
