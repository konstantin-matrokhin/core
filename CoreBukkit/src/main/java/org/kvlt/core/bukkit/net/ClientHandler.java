package org.kvlt.core.bukkit.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.bukkit.Bukkit;
import org.kvlt.core.bukkit.ConfigManager;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.bukkit.packets.ConnectPacket;
import org.kvlt.core.protocol.PacketIn;

public class ClientHandler extends SimpleChannelInboundHandler<PacketIn> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Bukkit.getLogger().info("§bСоединено с главным сервером.");
        CorePlugin.getAPI().setServer(ctx.channel());

        String name = ConfigManager.getClientName();
        short port = (short) Bukkit.getPort();

        new ConnectPacket(name, port).send();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, PacketIn packetIn) {
        packetIn.execute(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable error) {
        error.printStackTrace();
        ConnectionManager.get().setConnected(false);
        ConnectionManager.get().connect();
    }

}
