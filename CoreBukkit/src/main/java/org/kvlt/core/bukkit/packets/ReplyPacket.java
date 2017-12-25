package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.REPLY_PACKET;

public class ReplyPacket extends BukkitPacketOut {

    private String sender;
    private String message;

    public ReplyPacket(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(sender, out);
        PacketUtil.writeString(message, out);
    }

    @Override
    public int getId() {
        return REPLY_PACKET;
    }

}
