package org.kvlt.core.bungee;

import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.kvlt.core.bungee.auth.Auth;
import org.kvlt.core.bungee.packets.PlayerLoginPacket;
import org.kvlt.core.bungee.packets.PlayerQuitPacket;
import org.kvlt.core.bungee.packets.SwitchServerPacket;
import org.kvlt.core.bungee.storages.PremiumQueue;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

public class ProxyEventListener implements Listener {

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        PendingConnection c = event.getConnection();
        String name = c.getName();
        boolean isPremium = CoreBungee.getAPI().getPremiumPlayers().contains(name)
                || PremiumQueue.has(name);

        if (isPremium) {
            c.setOnlineMode(true);
        }
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        String name = player.getName();
        String host = player.getAddress().getHostName();

        PlayerLoginPacket plp = new PlayerLoginPacket(name, host);
        plp.send();
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

        Auth.getAnnoyingMessages().remove(p);

        PlayerQuitPacket pqp = new PlayerQuitPacket(p.getName());
        pqp.send();
    }

}
