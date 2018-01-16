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
import org.kvlt.core.protocol.PacketOut;

import java.util.concurrent.TimeUnit;

public class ConnectionManager {

    public static final long RECONNECT_DELAY = 3L;

    private static ConnectionManager instance;

    private EventLoopGroup eventLoopGroup;
    private Bootstrap bootstrap;
    private Channel channel;

    private String host;
    private int port;
    private boolean connected;

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

    public void connect() {

        System.out.println("Подключение к кору..");

        if (connected) return;
        try {
            ChannelFuture channelFuture = bootstrap.connect(host, port);
            channel = channelFuture.channel();
            channelFuture.addListener((ChannelFuture future) -> {
                connected = future.isSuccess();
                if (!connected) {
                    System.out.println("Нет связи с главным сервером. Переподключение через 3 сек..");
                    future.channel().eventLoop().schedule(this::connect, RECONNECT_DELAY, TimeUnit.SECONDS);
                } else {
                    System.out.println("Подключено!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPacket(PacketOut packet) {
        channel.writeAndFlush(packet, channel.voidPromise());
    }

    public void sendPackets(PacketOut... packets) {
        for (PacketOut p: packets) {
            channel.write(p, channel.voidPromise());
        }
        channel.flush();
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
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
