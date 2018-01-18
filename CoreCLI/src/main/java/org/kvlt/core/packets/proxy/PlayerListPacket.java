package org.kvlt.core.packets.proxy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

@Deprecated
public class PlayerListPacket implements PacketIn {

    private String[] players;

    @Override
    public void read(ByteBuf in) {
        players = PacketUtil.readStringArray(in);
    }

    @Override
    public void execute(Channel channel) {
        for (String player: players) {
        }
    }

    @Override
    public int getId() {
        return Packets.PLAYER_PACKET;
    }
}
