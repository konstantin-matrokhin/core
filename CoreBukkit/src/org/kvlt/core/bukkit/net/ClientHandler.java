package org.kvlt.core.bukkit.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.bukkit.Bukkit;
import org.kvlt.core.bukkit.ConfigManager;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.packets.bukkit.ServerConnectionPacket;

public class ClientHandler extends SimpleChannelInboundHandler<Packet> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Bukkit.getLogger().info("Соединено с главным сервером!");
        CorePlugin.get().setServer(ctx.channel());
        ctx.writeAndFlush(new ServerConnectionPacket(ConfigManager.getClientName()));
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        packet.execute();
        System.out.println("new packet: " + packet.getClass().getSimpleName());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable error) {
        error.printStackTrace();
        ConnectionManager.get().connect();
    }

}
