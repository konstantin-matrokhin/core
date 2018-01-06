package org.kvlt.core.bukkit.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.bukkit.packets.MessagePacket;
import org.kvlt.core.protocol.PacketDecoder;
import org.kvlt.core.protocol.PacketEncoder;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketResolver;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    private static PacketResolver resolver;

    static {
        resolver = CorePlugin.get().getPacketResolver();

        PacketIn[] packets = {
                new MessagePacket()
        };

        resolver.registerPackets(packets);
    }

    @Override
    public void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(
                new PacketEncoder(),
                new PacketDecoder(resolver),
                new ClientHandler()
        );
    }

}
