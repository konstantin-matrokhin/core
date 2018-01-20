package org.kvlt.core.bungee.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.packets.ConnectPacket;
import org.kvlt.core.protocol.PacketIn;

import java.util.Collection;

public class BungeeHandler extends SimpleChannelInboundHandler<PacketIn> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        CoreBungee.get().setCoreServer(ctx.channel());

        new ConnectPacket(CoreBungee.get().getServerName()).send();
        Collection<ProxiedPlayer> players = ProxyServer.getInstance().getPlayers();
        if (players.size() > 0) {
//            new PlayerPacket(players).send();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ConnectionManager.get().setConnected(false);
        ConnectionManager.get().connect();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PacketIn packetIn) {
        packetIn.execute(ctx.channel());
    }

}
