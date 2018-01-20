package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class GListPacket extends BukkitPacketOut {

    private String sender;
    private String pattern;

    public GListPacket(String sender, String pattern) {
        this.sender = sender;
        this.pattern = pattern;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(sender, out);
        PacketUtil.writeString(pattern, out);
    }

    @Override
    public int getId() {
        return Packets.G_LIST_PACKET;
    }

}
