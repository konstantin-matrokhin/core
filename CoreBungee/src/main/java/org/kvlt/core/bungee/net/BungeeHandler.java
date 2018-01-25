package org.kvlt.core.bungee.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;
import org.kvlt.core.bungee.Core;
import org.kvlt.core.bungee.events.ConnectedEvent;
import org.kvlt.core.bungee.events.PacketEvent;
import org.kvlt.core.bungee.packets.ConnectPacket;
import org.kvlt.core.protocol.PacketIn;

import java.util.Collection;

public class BungeeHandler extends SimpleChannelInboundHandler<PacketIn> {

    private ConnectionManager connectionManager;

    {
        connectionManager = Core.getAPI().getConnectionManager();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Event connectedEvent = new ConnectedEvent(ctx.channel());
        ProxyServer.getInstance().getPluginManager().callEvent(connectedEvent);


        new ConnectPacket(Core.getAPI().getProxyName()).send();
        Collection<ProxiedPlayer> players = ProxyServer.getInstance().getPlayers();
        if (players.size() > 0) {
//            new PlayerPacket(players).send();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Core.getAPI().getConnectionManager().setConnected(false);
        Core.getAPI().connect();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PacketIn packetIn) {
        Event packetEvent = new PacketEvent(packetIn, packetIn.getId());
        ProxyServer.getInstance().getPluginManager().callEvent(packetEvent);
        packetIn.execute(ctx.channel());
    }

}
