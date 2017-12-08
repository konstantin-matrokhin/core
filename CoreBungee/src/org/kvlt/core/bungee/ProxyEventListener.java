package org.kvlt.core.bungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.kvlt.core.bungee.packets.PlayerJoinPacket;

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
//
//    @EventHandler
//    public void onServerSwitch(ServerSwitchEvent event) {
//        ProxiedPlayer player = event.getPlayer();
//        String to = player.getServer().getInfo().getName();
//
//        PlayerSwitchServerPacketOld packet = new PlayerSwitchServerPacketOld(player.getName(), to);
//        //CoreBungee.get().sendPacket(packet);
//    }
//
//    @EventHandler
//    public void onDisconnect(PlayerDisconnectEvent event) {
//        ProxiedPlayer p = event.getPlayer();
//        PlayerProxyQuitPacketOld ppqp = new PlayerProxyQuitPacketOld(p.getName());
//        //CoreBungee.get().sendPacket(ppqp);
//    }

}
