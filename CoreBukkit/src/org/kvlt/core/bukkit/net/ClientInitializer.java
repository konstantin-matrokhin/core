package org.kvlt.core.bukkit.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.kvlt.core.protocol.PacketDecoder;
import org.kvlt.core.protocol.PacketEncoder;
import org.kvlt.core.protocol.PacketResolver;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        PacketResolver resolver = new BukkitPacketResolver();

        pipeline.addLast(
                new PacketEncoder(),
                new PacketDecoder(resolver),
                new ClientHandler()
        );
    }

}
