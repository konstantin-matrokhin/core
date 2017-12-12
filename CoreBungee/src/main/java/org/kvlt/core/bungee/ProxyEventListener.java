package org.kvlt.core.bungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.kvlt.core.bungee.packets.PlayerChatPacket;
import org.kvlt.core.bungee.packets.PlayerJoinPacket;
import org.kvlt.core.bungee.packets.PlayerQuitPacket;
import org.kvlt.core.bungee.packets.SwitchServerPacket;

public class ProxyEventListener implements Listener {

    @EventHandler
    public void onConnection(PostLoginEvent event) {
        ProxiedPlayer p = event.getPlayer();

        String name = p.getName();
        String ip = p.getAddress().getHostString();
        String uuid = p.getUniqueId().toString();

        PlayerJoinPacket playerJoinPacket = new PlayerJoinPacket(name, ip, uuid);
        playerJoinPacket.send();
    }

    @EventHandler
    public void onChat(ChatEvent event) {
        if (!(event.getSender() instanceof ProxiedPlayer)) return;

        ProxiedPlayer sender = (ProxiedPlayer) event.getSender();
        String message = event.getMessage();
        boolean isCommand = event.isCommand();

        PlayerChatPacket pcp = new PlayerChatPacket(sender.getName(), message, isCommand);
        pcp.send();
    }

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent event) {
        ProxiedPlayer player = event.getPlayer();
        String to = player.getServer().getInfo().getName();

        SwitchServerPacket ssp = new SwitchServerPacket(player.getName(), to);
        ssp.send();
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer p = event.getPlayer();

        PlayerQuitPacket pqp = new PlayerQuitPacket(p.getName());
        pqp.send();
    }

}
