package org.kvlt.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.kvlt.core.commands.BroadcastCommand;
import org.kvlt.core.commands.Command;
import org.kvlt.core.commands.CommandListener;
import org.kvlt.core.commands.WhoCommand;
import org.kvlt.core.config.Config;
import org.kvlt.core.net.CoreInitializer;
import org.kvlt.core.nodes.GameServers;
import org.kvlt.core.nodes.Proxies;
import org.kvlt.core.utils.Log;

public class CoreServer {

    private static CoreServer instance;
    private ServerPlayers serverPlayers;
    private OnlinePlayers onlinePlayers;
    private Proxies proxies;
    private GameServers gameServers;
    private int port;

    private CoreServer() {
        serverPlayers = new ServerPlayers();
        onlinePlayers = new OnlinePlayers();
        proxies = new Proxies();
        gameServers = new GameServers();
        port = Integer.valueOf(Config.getCore("port"));
    }

    public void start() {

        Log.$("Прослушиваю порт " + port + "..");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new CoreInitializer());

            ChannelFuture future = bootstrap.bind(port);
            future.addListener((channelFuture) -> {
                Log.$(
                        channelFuture.isSuccess()
                                ? "Сервер запущен с портом " + port
                                : "Не удалось запустить сервер"
                );
            });

            CommandListener cl = new CommandListener();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerPlayers getServerPlayers() {
        return serverPlayers;
    }

    public OnlinePlayers getOnlinePlayers() {
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
