package net.enot.darta;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import net.enot.darta.logger.DartaLogger;
import net.enot.darta.threads.ProxySendOnline;

import java.util.logging.Level;

/**
 * Created by Енот on 17.04.2017.
 */
public class Darta {

    private static final boolean epoll = Epoll.isAvailable();
    private static final int PORT = 5050;

    private static Darta instance;

    public static Darta getInstance() {
        return instance;
    }

    private static DartaLogger logger;

    public DartaLogger getLogger() {
        return logger;
    }

    public static void main(String[] args) throws Exception {
        logger = new DartaLogger();
        new Darta();
    }


    private Darta() {
        instance = this;
        if (epoll){
            logger.log(Level.INFO, "Доступен Epoll, начинаю работу с ним");
        }
        EventLoopGroup eventLoopGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(eventLoopGroup)
                .channel(epoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new DartaInitializer());
        ChannelFuture channelFuture = serverBootstrap.bind(PORT);
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                logger.log(Level.INFO, "Darta успешно включена на порту " + PORT);
                new ProxySendOnline().start();
            } else {
                logger.log(Level.WARNING, "Ошибка запуска Darta:");
                future.cause().printStackTrace();
            }
        });
    }
}