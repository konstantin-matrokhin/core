package org.kvlt.core.bungee.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.packets.KickPacket;
import org.kvlt.core.bungee.packets.MessagePacket;
import org.kvlt.core.bungee.packets.MotdPacket;
import org.kvlt.core.bungee.packets.PingPacket;
import org.kvlt.core.protocol.PacketDecoder;
import org.kvlt.core.protocol.PacketEncoder;
import org.kvlt.core.protocol.PacketResolver;

public class ProxyInitializer extends ChannelInitializer<SocketChannel> {

    private static PacketResolver resolver;

    static {
        resolver = CoreBungee.get().getPacketResolver();

        resolver.registerPacket(new KickPacket());
        resolver.registerPacket(new MotdPacket());
        resolver.registerPacket(new MessagePacket());
    }

    @Override
    public void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(
                new PacketEncoder(),
                new PacketDecoder(resolver),
                new BungeeHandler()
        );
    }

}
