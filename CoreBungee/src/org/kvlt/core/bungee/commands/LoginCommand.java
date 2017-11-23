package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.packets.player.PlayerAuthPacket;

public class LoginCommand extends Command {

    public LoginCommand() {
        super("login", null, "l");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length != 1) return;
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer p = (ProxiedPlayer) commandSender;
        String password = args[0];

        PlayerAuthPacket pap = new PlayerAuthPacket(p.getName(), password);
        CoreBungee.get().getCoreServer().writeAndFlush(pap);
    }
}
