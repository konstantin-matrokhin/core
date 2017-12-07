package org.kvlt.core.bukkit.net;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.bukkit.Bukkit;
import org.kvlt.core.bukkit.ConfigManager;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.protocol.PacketIn;

public class ClientHandler extends SimpleChannelInboundHandler<PacketIn> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Bukkit.getLogger().info("Соединено с главным сервером!");
        CorePlugin.get().setServer(ctx.channel());

        String name = ConfigManager.getClientName();
        int port = Bukkit.getPort();
        String ip = Bukkit.getIp();

        //ctx.writeAndFlush(new ServerConnectPacket(name, port), ctx.voidPromise());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, PacketIn packetIn) {
        packetIn.execute();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable error) {
        error.printStackTrace();
        ConnectionManager.get().connect();
    }

}
