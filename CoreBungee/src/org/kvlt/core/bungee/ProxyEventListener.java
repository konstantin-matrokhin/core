package org.kvlt.core.bungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.kvlt.core.bungee.entities.PlayerAdapter;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.player.PlayerProxyLoginPacket;
import org.kvlt.core.packets.player.PlayerProxyQuitPacket;
import org.kvlt.core.packets.player.PlayerSwitchServerPacket;

public class ProxyEventListener implements Listener {

    @EventHandler
    public void onPostLogin(ServerConnectedEvent event) {
        ProxiedPlayer p = event.getPlayer();
        OnlinePlayer player = PlayerAdapter.asOnlinePlayer(p);
        String proxyName = CoreBungee.get().getServerName();
        String serverName = event.getServer().getInfo().getName();

        player.setIp(p.getAddress().getHostString());

        PlayerProxyLoginPacket plp = new PlayerProxyLoginPacket(player, proxyName, serverName);
        CoreBungee.get().getCoreServer().writeAndFlush(plp);
        System.out.println("sent " + p.getName());
    }

    @EventHandler
    public void onServerSwitch(ServerSwitchEvent event) {
        ProxiedPlayer player = event.getPlayer();
        String to = player.getServer().getInfo().getName();

        PlayerSwitchServerPacket packet = new PlayerSwitchServerPacket(player.getName(), to);
        CoreBungee.get().getCoreServer().writeAndFlush(packet);
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer p = event.getPlayer();
        PlayerProxyQuitPacket ppqp = new PlayerProxyQuitPacket(p.getName());
        CoreBungee.get().getCoreServer().writeAndFlush(ppqp);
    }

}
