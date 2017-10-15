package org.kvlt.core.bungee.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.kvlt.core.bungee.CoreBungee;
import static org.kvlt.core.bungee.utils.Log.$;

public class ConnectionManager {

    private static ConnectionManager instance;

    private EventLoopGroup eventLoopGroup;
    private Channel channel;

    private ConnectionManager() {}

    public void startClient() {
        String host = CoreBungee.get().getConfig().getString("host");
        int port = CoreBungee.get().getConfig().getInt("port");

        eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ProxyInitializer());

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channel = channelFuture.channel();
            channelFuture.addListener((future) -> {
                if (future.isSuccess()) {
                    //
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
                $("Отключено от главного сервера");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

