package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.CoreDB;
import org.kvlt.core.bungee.packets.LogoutPacket;
import org.kvlt.core.bungee.storages.IdMap;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

import java.sql.PreparedStatement;

public class LogoutCommand extends Command {

    public LogoutCommand() {
        super("logout", null, "lo");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) sender;
        String name = player.getName();

        if (ProxyLoggedPlayers.isLogged(name)) {
                int id = IdMap.getId(name);
                ProxyLoggedPlayers.logOut(name);
                new LogoutPacket(name).send();
        } else {
            player.sendMessage(new TextComponent("Сначала авторизуйтесь!"));
        }
    }
}
