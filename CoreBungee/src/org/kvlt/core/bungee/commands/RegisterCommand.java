package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.packets.player.PlayerRegisterPacket;

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
            return;
        }

        PlayerRegisterPacket prp = new PlayerRegisterPacket(p.getName(), password);
        CoreBungee.get().sendPacket(prp);
    }
}
