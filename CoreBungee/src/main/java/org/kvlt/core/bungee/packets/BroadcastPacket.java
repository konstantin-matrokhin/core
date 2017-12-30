package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class BroadcastPacket implements PacketIn {

    private String message;

    @Override
    public void read(ByteBuf in) {
        message = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ProxyServer.getInstance().broadcast(new TextComponent(message));
    }

    @Override
    public int getId() {
        return Packets.BROADCAST_PACKET;
    }

}
