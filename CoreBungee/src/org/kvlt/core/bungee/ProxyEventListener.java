package org.kvlt.core.bungee;

import net.md_5.bungee.api.plugin.Listener;

public class ProxyEventListener implements Listener {

//    @EventHandler
//    public void onConnection(PostLoginEvent event) {
//        ProxiedPlayer p = event.getPlayer();
//        OnlinePlayer player = PlayerAdapter.asOnlinePlayer(p);
//        String proxyName = CoreBungee.get().getServerName();
//
//        player.setIp(p.getAddress().getHostString());
//
//        PlayerProxyLoginPacket plp = new PlayerProxyLoginPacket(player, proxyName);
//        //CoreBungee.get().sendPacket(plp);
//    }
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
