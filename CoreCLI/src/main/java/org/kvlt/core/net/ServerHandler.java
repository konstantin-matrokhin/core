package org.kvlt.core.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.kvlt.core.events.network.PacketEvent;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.utils.Log;

public class ServerHandler extends SimpleChannelInboundHandler<PacketIn> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        Log.$("Новое подключение: " + channel.remoteAddress());
        ClientManager.add(channel);

        channel.closeFuture().addListener((future) -> {
            ClientManager.remove(channel);
            Log.$("Разорвано соединение: " + channel.remoteAddress());
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ctx.close();
        ClientManager.remove(ctx.channel());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, PacketIn packetIn) {
        Channel channel = ctx.channel();
        PacketEvent event = new PacketEvent(packetIn, channel);

        event.invoke();
        packetIn.execute(channel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Log.err(String.format("Отсоединен %s  %s",
                ctx.channel().remoteAddress(),
                cause.getLocalizedMessage()));

        cause.printStackTrace();
    }

}