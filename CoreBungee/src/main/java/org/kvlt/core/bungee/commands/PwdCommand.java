package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.packets.PwdPacket;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

public class PwdCommand extends Command {

    public PwdCommand() {
        super("changepassword", null, "pwd", "changepass", "passwd", "passwordchange");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) sender;
        String name = player.getName();

        if (!ProxyLoggedPlayers.isLogged(name)) return;

        String oldPassword = args[0];
        String newPassword = args[1];

        if (!oldPassword.equals(newPassword)) {
            new PwdPacket(name, oldPassword, newPassword).send();
        } else {
            player.sendMessage(new TextComponent("Пароли совпадают!"));
        }
    }
}
