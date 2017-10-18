package org.kvlt.core.bukkit.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.bukkit.Bukkit;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.packets.Packet;

public class ClientHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Bukkit.getLogger().info("Соединено с главным сервером!");
        CorePlugin.get().setServer(ctx.channel());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        packet.execute();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable error) {
        ConnectionManager.get().connect();
    }

}
