package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerInfoPacket extends BukkitPacketOut {

    private String senderName;
    private String playerName;
    private boolean full;

    public PlayerInfoPacket(String senderName, String playerName, boolean full) {
        this.senderName = senderName;
        this.playerName = playerName;
        this.full = full;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(senderName, out);
        PacketUtil.writeString(playerName, out);
        out.writeBoolean(full);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_INFO_PACKET;
    }

}
