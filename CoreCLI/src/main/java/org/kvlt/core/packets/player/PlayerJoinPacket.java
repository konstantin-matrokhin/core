package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.Packets;

public class PlayerJoinPacket extends PlayerPacket {



    @Override
    public void read(ByteBuf in) {

    }

    @Override
    public void execute(Channel channel) {

    }

    @Override
    public int getId() {
        return Packets.PLAYER_JOIN_PACKET;
    }
}
