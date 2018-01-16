package org.kvlt.core.net;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.kvlt.core.Core;
import org.kvlt.core.packets.bukkit.ServerConnectPacket;
import org.kvlt.core.packets.bukkit.ServerDisconnectPacket;
import org.kvlt.core.packets.player.*;
import org.kvlt.core.packets.proxy.ProxyConnectPacket;
import org.kvlt.core.packets.proxy.ProxyDisconnectPacket;
import org.kvlt.core.protocol.*;

public class CoreInitializer extends ChannelInitializer<SocketChannel> {

    private static PacketResolver resolver;

    static {
        resolver = Core.get().getServer().getPacketResolver();

        PacketIn[] packets = {
                new ProxyConnectPacket(),
                new ServerConnectPacket(),
                new ProxyDisconnectPacket(),
                new ServerDisconnectPacket(),
                new PlayerJoinPacket(),
                new PlayerQuitPacket(),
                new PlayerSwitchServerPacket(),
                new PlayerChatPacket(),
                new PlayerRegisterPacket(),
                new PlayerLoginPacket(),
                new PlayerPreLoginPacket(),
                new PrivateMessagePacket(),
                new ReplyPacket(),
                new EmailAddPacket(),
                new EmailVerifyPacket(),
                new PasswordRecoveryPacket(),
                new PwdPacket(),
                new BanPacket(),
                new KickRequestPacket(),
                new EmailChangePacket(),
                new EmailChangeVerifyPacket(),
                new TransferRequestPacket(),
                new PremiumPlayerPacket(),
                new HubRequestPacket()
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
                new ServerHandler()
        );
    }

}
