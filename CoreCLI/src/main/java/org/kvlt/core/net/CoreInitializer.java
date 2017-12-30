package org.kvlt.core.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.packets.player.*;
import org.kvlt.core.packets.proxy.*;
import org.kvlt.core.packets.bukkit.*;
import org.kvlt.core.protocol.PacketDecoder;
import org.kvlt.core.protocol.PacketEncoder;
import org.kvlt.core.protocol.PacketFramer;
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
        resolver.registerPacket(new PlayerRegisterPacket());
        resolver.registerPacket(new PlayerLoginPacket());
        resolver.registerPacket(new PlayerPreLoginPacket());
        resolver.registerPacket(new PrivateMessagePacket());
        resolver.registerPacket(new ReplyPacket());
        resolver.registerPacket(new EmailAddPacket());
        resolver.registerPacket(new EmailVerifyPacket());
    }

    @Override
    public void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(
                new PacketFramer(),
                new PacketEncoder(),
                new PacketDecoder(resolver),
                new ServerHandler()
        );
    }

}
