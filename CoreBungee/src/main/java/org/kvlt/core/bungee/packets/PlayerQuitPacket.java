package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeeOutPacket;
import org.kvlt.core.protocol.PacketUtil;

public class PlayerQuitPacket extends BungeeOutPacket {

    private String playerName;

    public PlayerQuitPacket(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(playerName, out);
    }

    @Override
    public int getId() {
        return 7;
    }
}
