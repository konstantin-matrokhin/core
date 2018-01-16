package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.CoreDB;
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

                player.disconnect(new TextComponent("Logout."));
                ProxyServer.getInstance().getScheduler().runAsync(CoreBungee.get(), () -> {
                    try {
                        PreparedStatement statement = CoreDB.get().getConnection()
                                .prepareStatement("UPDATE join_info SET ip = NULL WHERE id = ?");
                        statement.setInt(1, id);
                        statement.executeUpdate();
                    } catch (Exception ignored) {}
                    ProxyLoggedPlayers.logOut(name);
                });
        } else {
            player.sendMessage(new TextComponent("Сначала авторизуйтесь!"));
        }
    }
}
