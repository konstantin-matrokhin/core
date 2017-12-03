package org.kvlt.core.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.kvlt.core.packets.PacketOld;
import org.kvlt.core.utils.Log;

public class ServerHandler extends SimpleChannelInboundHandler<PacketOld> {

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

    /**
     * Выполняет, принимая контекст пакета
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, PacketOld packet) throws Exception {
        packet.execute(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Log.$("Произошла ошибка с подключением: " + ctx.channel().remoteAddress());
        cause.printStackTrace();
        channelInactive(ctx);
    }

}