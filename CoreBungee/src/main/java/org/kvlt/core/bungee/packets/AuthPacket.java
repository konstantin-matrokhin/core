package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketIn;

import static org.kvlt.core.protocol.Packets.PLAYER_AUTH_PACKET;

public class AuthPacket implements PacketIn {

    @Override
    public void read(ByteBuf in) {

    }

    @Override
    public void execute(Channel channel) {

    }

    @Override
    public int getId() {
        return PLAYER_AUTH_PACKET;
    }
}
