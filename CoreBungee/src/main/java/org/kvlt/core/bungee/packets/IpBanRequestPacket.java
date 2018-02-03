package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class IpBanRequestPacket extends BungeePacketOut {

    private String ip;

    public IpBanRequestPacket(String ip) {
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
