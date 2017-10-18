package org.kvlt.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.kvlt.core.config.Config;
import org.kvlt.core.net.CoreInitializer;
import org.kvlt.core.nodes.Proxies;
import org.kvlt.core.utils.Log;

public class CoreServer {

    private static CoreServer instance;
    private ServerPlayers serverPlayers;
    private Proxies proxies;
    private int port;

    private CoreServer() {
        serverPlayers = new ServerPlayers();
        proxies = new Proxies();
        port = Integer.valueOf(Config.getCore("port"));
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new CoreInitializer());

            ChannelFuture future = bootstrap.bind(port).sync();
            future.addListener((channelFuture) -> {
                Log.$(
                        channelFuture.isSuccess()
                                ? "Сервер запущен с портом " + port
                                : "Не удалось запустить сервер"
                );
            });

            new CommandListener();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerPlayers getServerPlayers() {
        return serverPlayers;
    }

    public Proxies getProxies() {
        return proxies;
    }

    public static synchronized CoreServer get() {
        return instance == null ? instance = new CoreServer() : instance;
    }

}
