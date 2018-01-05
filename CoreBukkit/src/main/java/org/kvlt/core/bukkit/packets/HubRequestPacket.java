package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class HubRequestPacket extends BukkitPacketOut {

    private String player;

    public HubRequestPacket(String player) {
        this.player = player;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player, out);
    }

    @Override
    public int getId() {
        return Packets.HUB_PACKET;
    }

}
