package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class LoginCommand extends Command {

    public LoginCommand() {
        super("login", null, "l");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
//        if (args.length != 1) return;
//        if (!(commandSender instanceof ProxiedPlayer)) return;
//
//        ProxiedPlayer p = (ProxiedPlayer) commandSender;
//        String password = args[0];
//
//        PlayerAuthPacketOld pap = new PlayerAuthPacketOld(p.getName(), password);
//        CoreBungee.get().sendPacket(pap);
    }
}
