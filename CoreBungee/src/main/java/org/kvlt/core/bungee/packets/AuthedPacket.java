package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class AuthedPacket extends BungeePacketOut {

    private String player;

    public AuthedPacket(String player) {
        this.player = player;
    }

    @Override
    public void write(ByteBuf in) {
        PacketUtil.writeString(player, in);
    }

    @Override
    public int getId() {
        return Packets.SUCCESS_AUTH_PACKET;
    }

}
