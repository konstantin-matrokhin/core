package org.kvlt.core.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

public class ServerHandler extends SimpleChannelInboundHandler<Packet> {

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

    /**
     * Выполняет, принимая контекст пакета
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        packet.execute(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Log.$("Произошла ошибка с подключением: " + ctx.channel().remoteAddress());
        ClientManager.remove(ctx.channel());
        ctx.close();
        cause.printStackTrace();
    }

}