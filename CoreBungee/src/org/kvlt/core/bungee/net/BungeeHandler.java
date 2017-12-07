package org.kvlt.core.bungee.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.packets.ConnectPacket;
import org.kvlt.core.protocol.PacketIn;

public class BungeeHandler extends SimpleChannelInboundHandler<PacketIn> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        CoreBungee.get().setCoreServer(ctx.channel());

        ConnectPacket connectPacket = new ConnectPacket(CoreBungee.get().getServerName());
        connectPacket.send();
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

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    }

}
