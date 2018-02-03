package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class IpBanPacket extends CorePacketOut {

    private String ip;

    public IpBanPacket(String ip) {
        this.ip = ip;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(ip, out);
    }

    @Override
    public int getId() {
        return Packets.BAN_IP;
    }

}
