package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class AuthPacket extends CorePacketOut {

    private String playerName;

    public AuthPacket(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(playerName, out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_AUTH_PACKET;
    }
}
