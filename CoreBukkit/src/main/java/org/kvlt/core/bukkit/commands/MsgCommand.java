package org.kvlt.core.bukkit.commands;

import net.lastcraft.api.command.CommandInterface;
import net.lastcraft.api.command.SpigotCommand;
import net.lastcraft.entity.GamerEntity;
import org.kvlt.core.bukkit.packets.PrivateMessagePacket;

import java.util.Arrays;

public class MsgCommand implements CommandInterface {

    public MsgCommand() {
        SpigotCommand command = getCommandsAPI()
                .register("msg", this,
                        "pm", "whisper", "tell", "message", "m", "t", "w");
        command.setOnlyPlayers(true);
    }

    @Override
    public void execute(GamerEntity sender, String s, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("/tell <ник> <сообщение>");
            return;
        }

        String recipient = args[0];
        String[] words = Arrays.copyOfRange(args, 1, args.length);
        String message = String.join(" ", words);

        PrivateMessagePacket msgPacket = new PrivateMessagePacket(
                sender.getName(),
                recipient,
                message);
        msgPacket.send();
    }
}
