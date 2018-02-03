package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerDataRequestPacket extends BukkitPacketOut {

    private String playerName;
    private int key;

    public PlayerDataRequestPacket(String playerName) {
        key = random.nextInt(999999);
        this.playerName = playerName;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(playerName, out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_DATA_PACKET;
    }

    @Override
    public int getKey() {
        return key;
    }

}
