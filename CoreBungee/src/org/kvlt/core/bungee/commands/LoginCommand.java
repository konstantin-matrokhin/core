package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.packets.player.PlayerAuthPacket;

public class LoginCommand extends Command {

    public LoginCommand() {
        super("login", null, "l");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        PlayerAuthPacket pap = new PlayerAuthPacket(commandSender.getName(), args[0]);
        CoreBungee.get().getCoreServer().writeAndFlush(pap);
    }
}
