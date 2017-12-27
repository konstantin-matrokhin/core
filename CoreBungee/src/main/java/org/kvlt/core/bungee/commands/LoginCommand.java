package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.auth.Auth;

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

        if (Auth.passwordAuth(p.getName(), password)) {
            p.sendMessage(new TextComponent("Вы успешно вошли!"));
        }

    }

}
