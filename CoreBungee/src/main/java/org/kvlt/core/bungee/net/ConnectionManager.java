package org.kvlt.core.bungee.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.utils.BungeeLog;

import java.util.concurrent.TimeUnit;

public class ConnectionManager {

    public static final long RECONNECT_DELAY = 3L;

    private static ConnectionManager instance;

    private EventLoopGroup eventLoopGroup;
    private Bootstrap bootstrap;
    private Channel channel;
    private String host;
    private int port;

    private volatile boolean isConnected;
    private volatile boolean disconnecting;

    private ConnectionManager() {}

    public void startClient() {
        host = CoreBungee.get().getConfig().getString("core.host");
        port = CoreBungee.get().getConfig().getInt("core.port");
        eventLoopGroup = new NioEventLoopGroup();

        try {
            bootstrap = new Bootstrap()
                    .group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ProxyInitializer());

            connect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        if (isConnected) return;
        if (disconnecting) return;

        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port);
            channel = channelFuture.channel();
            channelFuture.addListener((ChannelFuture future) -> {
                isConnected = future.isSuccess();

                if (!isConnected) {
                    BungeeLog.$("Нет связи с главным сервером. Переподключение через 3 сек..");
                    future.channel().eventLoop().schedule(this::connect, RECONNECT_DELAY, TimeUnit.SECONDS);
                } else {
                    BungeeLog.$("Подключено!");
                }
            });
        } catch (Exception e) {

        }
    }

    public Channel getChannel() {
        return channel;
    }

    public static synchronized ConnectionManager get() {
        return instance == null ? instance = new ConnectionManager() : instance;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void disconnect() {
        try {
            if (eventLoopGroup.shutdownGracefully().sync().isSuccess()) {
                BungeeLog.$("Отключено от главного сервера");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isDisconnecting() {
        return disconnecting;
    }

    public void setDisconnecting(boolean disconnecting) {
        this.disconnecting = disconnecting;
    }
}

