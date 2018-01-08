package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PremiumListPacket extends CorePacketOut {

    private String[] names;

    public PremiumListPacket(String[] names) {
        this.names = names;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeStringArray(names, out);
    }

    @Override
    public int getId() {
        return Packets.PREMIUM_LIST_PACKET;
    }

}
