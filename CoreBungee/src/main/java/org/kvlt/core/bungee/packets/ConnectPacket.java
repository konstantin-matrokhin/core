package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeeOutPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class ConnectPacket extends BungeeOutPacket {

    private String name;

    public ConnectPacket(String name) {
        this.name = name;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(name, out);
    }

    @Override
    public int getId() {
        return Packets.PROXY_CONNECT_PACKET;
    }
}
