package net.enot.dartabungee.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.enot.dartasystem.packets.player.PlayerRejoinPacket;
import net.enot.dartasystem.packets.proxy.ProxyConnectionPacket;
import net.enot.dartasystem.utils.DartaSystemUtil;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;

import java.util.concurrent.TimeUnit;

/**
 * Created by Енот on 23.04.2017.
 */
public class DartaClient {

    private static final boolean epoll = Epoll.isAvailable();
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 5050;

    private EventLoopGroup eventLoopGroup;
    private Bootstrap bootstrap;
    private ChannelFuture channelFuture;
    private Channel channel;
    private boolean connected = false;

    public DartaClient(){
        eventLoopGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        doConnect();
    }

    public void doConnect() {
        bootstrap = new Bootstrap();
        bootstrap
                .group(eventLoopGroup)
                .channel(epoll ? EpollSocketChannel.class : NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new DartaInitializer());
        channelFuture = bootstrap.connect(HOST, PORT).addListener((ChannelFuture future) -> {
            if (future.isSuccess()) {
                channel = channelFuture.channel();
                write(new ProxyConnectionPacket(DartaSystemUtil.getFolder()));
                for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                    Server server = player.getServer();
                    write(new PlayerRejoinPacket(player.getName(), server.getInfo().getName()));
                }
                connected = true;
                System.out.println("Good <--------------------------------------");
            } else {
                connected = false;
                System.out.println("Recconect <--------------------------------------");
                future.channel().eventLoop().schedule(this::doConnect, 1, TimeUnit.SECONDS);
            }
        });
    }

    public boolean isConnected(){
        return connected;
    }

    public void write(Object packet) {
        channel.writeAndFlush(packet);
    }

    public void close() {
        eventLoopGroup.shutdownGracefully();
    }
}
