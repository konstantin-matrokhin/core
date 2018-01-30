package org.kvlt.core.bungee.listener;

import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.auth.Auth;
import org.kvlt.core.bungee.packets.LoginPacket;
import org.kvlt.core.bungee.packets.PlayerQuitPacket;
import org.kvlt.core.bungee.packets.PreLoginPacket;
import org.kvlt.core.bungee.packets.SwitchServerPacket;
import org.kvlt.core.bungee.storages.PremiumQueue;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

public class ProxyEventListener implements Listener {

    @EventHandler
    public void onPreLogin(PreLoginEvent event) {
        PendingConnection c = event.getConnection();
        String name = c.getName();
        String host = c.getAddress().getHostName();

        new PreLoginPacket(name, host).send();

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

        LoginPacket plp = new LoginPacket(name);
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
