package org.kvlt.core.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.packets.ProxyConnectPacket;
import org.kvlt.core.packets.ServerConnectPacket;
import org.kvlt.core.protocol.PacketDecoder;
import org.kvlt.core.protocol.PacketEncoder;
import org.kvlt.core.protocol.PacketResolver;

public class CoreInitializer extends ChannelInitializer<SocketChannel> {

    private static PacketResolver resolver;

    static {
        resolver = CoreServer.get().getPacketResolver();

        resolver.registerPacket(new ProxyConnectPacket());
        resolver.registerPacket(new ServerConnectPacket());
    }

    @Override
    public void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(
                new PacketEncoder(),
                new PacketDecoder(resolver),
                new ServerHandler()
        );
    }

}
