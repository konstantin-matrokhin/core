package org.kvlt.core.bungee.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.kvlt.core.bungee.Core;
import org.kvlt.core.bungee.packets.*;
import org.kvlt.core.protocol.*;

public class ProxyInitializer extends ChannelInitializer<SocketChannel> {

    private static PacketResolver resolver;

    static {
        resolver = Core.getAPI().getPacketResolver();

        PacketIn[] packets = {
                new KickPacket(),
                new MotdPacket(),
                new MessagePacket(),
                new AuthPacket(),
                new IdPacket(),
                new BroadcastPacket(),
                new TransferPacket(),
                new PremiumListPacket(),

        };

        resolver.registerPackets(packets);
    }

    @Override
    public void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(
                new PacketFramer(),
                new PacketEncoder(),
                new PacketDecoder(resolver),
                new BungeeHandler()
        );
    }

}
