package org.kvlt.core.packets.proxy;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class BroadcastPacket extends CorePacketOut {

    private String message;

    public BroadcastPacket(String message) {
        this.message = message;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(message, out);
    }

    @Override
    public void send() {

    }

    @Override
    public int getId() {
        return Packets.BROADCAST_PACKET;
    }

}
