package org.kvlt.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.kvlt.core.config.Config;
import org.kvlt.core.net.ClientManager;
import org.kvlt.core.net.CoreInitializer;
import org.kvlt.core.plugins.CorePlugin;
import org.kvlt.core.protocol.PacketResolver;
import org.kvlt.core.utils.Log;

public final class CoreServer implements Server {

    private ChannelFuture future;
    private PacketResolver packetResolver;
    private int port;

    CoreServer() {
        packetResolver = new PacketResolver();
        port = Integer.valueOf(Config.getCore("port"));
    }

    @Override
    public void start() {
        boolean hasEpoll = Epoll.isAvailable();

        EventLoopGroup bossGroup;
        EventLoopGroup workerGroup;
        Class<? extends ServerSocketChannel> socketChannelClass;

        if (hasEpoll) {
            bossGroup = new EpollEventLoopGroup();
            workerGroup = new EpollEventLoopGroup();
            socketChannelClass = EpollServerSocketChannel.class;
        } else {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
            socketChannelClass = NioServerSocketChannel.class;
        }

        try {
            Log.$("Прослушиваю порт " + port + "...");
            Log.$("Сокет: " + socketChannelClass.getSimpleName());

            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(socketChannelClass)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new CoreInitializer());

            future = bootstrap.bind(port);
            future.addListener((channelFuture) -> {
                String result;
                if (channelFuture.isSuccess()) {
                    result = "Сервер запущен!";
                } else {
                    result = "Не удалось запустить сервер :(";
                    future.cause().printStackTrace();
                }
                Log.$(result);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        Log.$("Остановка сервера...");
        ClientManager.getClients().disconnect();
        Core.get().getPluginManager().getPlugins().forEach(CorePlugin::onDisable);

        try {
            future.channel().close().sync();
            Log.$("Сервер выключен.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public PacketResolver getPacketResolver() {
        return packetResolver;
    }

    @Override
    public void restart() {
        stop();
        start();
    }

    @Override
    public int getPort() {
        return port;
    }

}
