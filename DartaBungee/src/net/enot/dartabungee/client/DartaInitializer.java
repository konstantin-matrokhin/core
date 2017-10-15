package net.enot.dartabungee.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import net.enot.dartasystem.handlers.FrameHandler;
import net.enot.dartasystem.handlers.PacketDecoder;
import net.enot.dartasystem.handlers.PacketEncoder;

/**
 * Created by Енот on 23.04.2017.
 */
public class DartaInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new FrameHandler());
        pipeline.addLast(new PacketEncoder());
        pipeline.addLast(new PacketDecoder());
        pipeline.addLast(new DartaHandler());
    }

}
