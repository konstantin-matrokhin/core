package org.kvlt.core.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.protocol.packets.bukkit.ServerConnectPacket;
import org.kvlt.core.protocol.packets.bukkit.ServerDisconnectPacket;
import org.kvlt.core.protocol.packets.player.PlayerChatPacket;
import org.kvlt.core.protocol.packets.player.PlayerJoinPacket;
import org.kvlt.core.protocol.packets.player.PlayerQuitPacket;
import org.kvlt.core.protocol.packets.player.PlayerSwitchServerPacket;
import org.kvlt.core.protocol.packets.proxy.ProxyConnectPacket;
import org.kvlt.core.protocol.packets.proxy.ProxyDisconnectPacket;
import org.kvlt.core.protocol.PacketDecoder;
import org.kvlt.core.protocol.PacketEncoder;
import org.kvlt.core.protocol.PacketResolver;

public class CoreInitializer extends ChannelInitializer<SocketChannel> {

    private static PacketResolver resolver;

    static {
        resolver = CoreServer.get().getPacketResolver();

        resolver.registerPacket(new ProxyConnectPacket());
        resolver.registerPacket(new ServerConnectPacket());
        resolver.registerPacket(new ProxyDisconnectPacket());
        resolver.registerPacket(new ServerDisconnectPacket());
        resolver.registerPacket(new PlayerJoinPacket());
        resolver.registerPacket(new PlayerQuitPacket());
        resolver.registerPacket(new PlayerSwitchServerPacket());
        resolver.registerPacket(new PlayerChatPacket());
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
