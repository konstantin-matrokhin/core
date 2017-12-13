package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.packets.RegisterPacket;

public class RegisterCommand extends Command {

    public RegisterCommand() {
        super("register", null, "reg");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length != 2) return;
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer p = (ProxiedPlayer) commandSender;

        String password = args[0];
        String passwordRepeat = args[1];

        if (!password.equals(passwordRepeat)) {
            p.sendMessage(new TextComponent("Пароли не совпадают"));
        } else {
            RegisterPacket rp = new RegisterPacket(p.getName(), password);
            rp.send();
        }

    }
}
