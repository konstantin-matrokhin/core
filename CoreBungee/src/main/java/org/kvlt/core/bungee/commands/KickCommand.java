package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.packets.KickRequestPacket;

import java.util.Arrays;
import java.util.Optional;

public class KickCommand extends Command {

    public KickCommand() {
        super("kick");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) return;

        String enforcer = sender.getName();
        String victim = args[0];
        String[] words = Arrays.copyOfRange(args, 1, args.length);
        String reason = Optional
                .ofNullable(String.join(" ", words))
                .orElse("Kicked!");

        new KickRequestPacket(enforcer, victim, reason).send();
    }

}
