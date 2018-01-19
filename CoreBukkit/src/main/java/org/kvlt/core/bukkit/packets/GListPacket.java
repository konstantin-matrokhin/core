package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class GListPacket extends BukkitPacketOut {

    private String sender;

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(sender, out);
    }

    @Override
    public int getId() {
        return Packets.G_LIST_PACKET;
    }

}
