package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.ID_PACKET;

public class IdPacket extends CorePacketOut {

    private String name;
    private int id;

    public IdPacket(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(name, out);
        out.writeInt(id);
    }

    @Override
    public void send() {

    }

    @Override
    public int getId() {
        return ID_PACKET;
    }
}
