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
import org.kvlt.core.commands.CommandListener;
import org.kvlt.core.config.Config;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.PlayerList;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.net.CoreInitializer;
import org.kvlt.core.nodes.GameServers;
import org.kvlt.core.nodes.Proxies;
import org.kvlt.core.utils.Log;

public class CoreServer {

    private static CoreServer instance;
    private PlayerList<ServerPlayer> serverPlayers;
    private PlayerList<OnlinePlayer> onlinePlayers;
    private Proxies proxies;
    private GameServers gameServers;
    private ChannelFuture future;
    private int port;

    private CoreServer() {
        serverPlayers = new PlayerList<>();
        onlinePlayers = new PlayerList<>();
        proxies = new Proxies();
        gameServers = new GameServers();
        port = Integer.valueOf(Config.getCore("port"));
    }

    void start() {

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
            Log.$("Прослушиваю порт " + port + " | Сокет: " + socketChannelClass.getSimpleName());

            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(socketChannelClass)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new CoreInitializer());

            future = bootstrap.bind(port);
            future.addListener((channelFuture) -> Log.$(
                    channelFuture.isSuccess()
                            ? "Сервер запущен с портом " + port
                            : "Не удалось запустить сервер"
            ));

            new CommandListener();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        Log.$("Остановка сервера...");
        try {
            future.channel().close().sync();
            Log.$("Сервер выключен.");
        } catch (Exception e) {

        }
    }

    public PlayerList<ServerPlayer> getServerPlayers() {
        return serverPlayers;
    }

    public PlayerList<OnlinePlayer> getOnlinePlayers() {
        return onlinePlayers;
    }

    public Proxies getProxies() {
        return proxies;
    }

    public GameServers getGameServers() {
        return gameServers;
    }

    public static synchronized CoreServer get() {
        return instance == null ? instance = new CoreServer() : instance;
    }

}
