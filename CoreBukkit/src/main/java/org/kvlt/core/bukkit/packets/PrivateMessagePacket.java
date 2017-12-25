package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PrivateMessagePacket extends BukkitPacketOut {

    private String sender;
    private String recipient;
    private String message;

    public PrivateMessagePacket(String sender, String recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(sender, out);
        PacketUtil.writeString(recipient, out);
        PacketUtil.writeString(message, out);
    }

    @Override
    public int getId() {
        return Packets.PM_PACKET;
    }
}
