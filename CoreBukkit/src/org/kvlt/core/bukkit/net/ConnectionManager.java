package org.kvlt.core.bukkit.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.kvlt.core.bukkit.ConfigManager;
import org.kvlt.core.bukkit.utils.Log;

import java.util.concurrent.TimeUnit;

public class ConnectionManager {

    private static ConnectionManager instance;

    private EventLoopGroup eventLoopGroup;
    private Bootstrap bootstrap;
    private Channel channel;
    private String host;
    private int port;
    private boolean isConnected;

    private ConnectionManager() {}

    public void startClient() {
        host = ConfigManager.config().getString("host");
        port = ConfigManager.config().getInt("port");
        eventLoopGroup = new NioEventLoopGroup();

        try {
            bootstrap = new Bootstrap()
                    .group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ClientInitializer());

            connect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void connect() {
        if (isConnected) return;
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channel = channelFuture.channel();
            channelFuture.addListener((ChannelFuture future) -> {
                isConnected = future.isSuccess();
                if (!isConnected) {
                    Log.$("Нет связи с главным сервером. Переподключение через 3 сек..");
                    future.channel().eventLoop().schedule(this::connect, 3L, TimeUnit.SECONDS);
                } else {
                    Log.$("Подключено!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public static synchronized ConnectionManager get() {
        return instance == null ? instance = new ConnectionManager() : instance;
    }

    public void disconnect() {
        try {
            if (eventLoopGroup.shutdownGracefully().sync().isSuccess()) {
                Log.$("Отключено от главного сервера");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
