package org.kvlt.core.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.kvlt.core.bungee.packets.*;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

public class ProxyEventListener implements Listener {

    @EventHandler
    public void onConnection(PostLoginEvent event) {
        ProxiedPlayer p = event.getPlayer();

        String name = p.getName();

        PlayerJoinPacket playerJoinPacket = new PlayerJoinPacket(name);
        playerJoinPacket.send();
    }

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        PendingConnection c = event.getConnection();

        PreLoginPacket plp = new PreLoginPacket(c.getName(), c.getAddress().getHostName(), c.getUniqueId().toString());
    }

    @EventHandler
    public void onLogin(LoginEvent event) {
        PendingConnection c = event.getConnection();

        LoginPacket lp = new LoginPacket(c.getName());
        lp.send();
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
        String name = p.getName();

        if (ProxyLoggedPlayers.isLogged(name)) {
            ProxyLoggedPlayers.logOut(name);
        }

        PlayerQuitPacket pqp = new PlayerQuitPacket(p.getName());
        pqp.send();
    }

}
