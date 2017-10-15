package org.kvlt.core.net;

import io.netty.channel.*;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

public class ServerHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        Log.$("connected: " + channel.remoteAddress());
        ClientManager.add(channel);
        channel.closeFuture().addListener((future) -> {
            ClientManager.remove(channel);
            Log.$("Отсоединен сервер: " + channel.remoteAddress());
        });
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        packet.execute();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Log.$("Произошла ошибка с сервером: " + ctx.channel().remoteAddress());
        cause.printStackTrace();
        ClientManager.remove(ctx.channel());
        ctx.close();
    }

}