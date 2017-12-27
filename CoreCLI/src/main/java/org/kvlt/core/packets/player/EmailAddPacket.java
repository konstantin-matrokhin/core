package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class EmailAddPacket extends PlayerPacket {

    private String email;

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
        email = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        if (ensurePlayer()) {

        }
    }

    @Override
    public int getId() {
        return Packets.EMAIL_ADD_PACKET;
    }

}
