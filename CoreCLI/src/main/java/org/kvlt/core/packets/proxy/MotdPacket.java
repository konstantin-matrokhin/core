package org.kvlt.core.packets.proxy;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.packets.CorePacketOut;

import static org.kvlt.core.protocol.Packets.PROXY_MOTD_PACKET;

public class MotdPacket extends CorePacketOut {

    private String motd;

    public MotdPacket(String motd) {
        this.motd = motd;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(motd, out);
    }

    @Override
    public void send() {

    }

    @Override
    public int getId() {
        return PROXY_MOTD_PACKET;
    }
}
