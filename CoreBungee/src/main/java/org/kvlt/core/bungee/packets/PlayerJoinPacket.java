package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerJoinPacket extends BungeePacketOut {

    private String playerName;

    public PlayerJoinPacket(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(playerName, out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_JOIN_PACKET;
    }

}
