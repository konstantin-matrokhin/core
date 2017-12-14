package org.kvlt.core.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.MESSAGE_PACKET;

public class MessagePacket extends CorePacketOut {

    private String recipient;
    private String message;

    public MessagePacket(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(recipient, out);
        PacketUtil.writeString(message, out);
    }

    @Override
    public void send() {

    }

    @Override
    public int getId() {
        return MESSAGE_PACKET;
    }
}
