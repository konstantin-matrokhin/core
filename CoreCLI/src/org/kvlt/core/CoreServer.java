package org.kvlt.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.kvlt.core.net.CoreInitializer;
import org.kvlt.core.nodes.Proxies;
import org.kvlt.core.utils.Log;

public class CoreServer {

    public static final int PORT = 3030;

    private static CoreServer instance;
    private ServerPlayers serverPlayers;
    private Proxies proxies;

    private CoreServer() {
        serverPlayers = new ServerPlayers();
        proxies = new Proxies();
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

            ChannelFuture future = bootstrap.bind(PORT).sync();
            future.addListener((channelFuture) -> {
                Log.$(
                        channelFuture.isSuccess()
                                ? "Сервер запущен по адресу " + future.channel().remoteAddress()
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
