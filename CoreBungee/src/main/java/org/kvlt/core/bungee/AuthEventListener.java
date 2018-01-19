package org.kvlt.core.bungee;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.kvlt.core.bungee.storages.Infractions;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

import java.util.ArrayList;
import java.util.List;

public class AuthEventListener implements Listener {

    private static List<String> allowedCmds;

    static {
        allowedCmds = new ArrayList<String>() {{
            add("/register");
            add("/reg");
            add("/l");
            add("/login");
            add("/email");
        }};
    }


    //TODO COOLDOWN
    @EventHandler
    public void onChat(ChatEvent event) {
        if (!(event.getSender() instanceof ProxiedPlayer)) return;

        ProxiedPlayer sender = (ProxiedPlayer) event.getSender();
        String playerName = sender.getName();
        String message = event.getMessage();

        if (Infractions.isMuted(playerName)) {
            if (!event.isCommand()) {
                sender.sendMessage(new TextComponent("У вас мут!"));
                event.setCancelled(true);
            }
        }

        if (!ProxyLoggedPlayers.isLogged(playerName)) {
            if (event.isCommand()) {
                String command = message.trim().split("\\s")[0];
                if (!allowedCmds.contains(command.toLowerCase())) {
                    sender.sendMessage(new TextComponent("Авторизуйтесь!"));
                    event.setCancelled(true);
                }
            } else {
                sender.sendMessage(new TextComponent("Авторизуйтесь!"));
                event.setCancelled(true);
            }
        }

//        PlayerChatPacket pcp = new PlayerChatPacket(sender.getName(), message, isCommand);
//        pcp.send();
    }

}
