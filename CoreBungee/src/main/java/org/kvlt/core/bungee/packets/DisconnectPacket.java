package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.packets.protocol.BungeeOutPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class DisconnectPacket extends BungeeOutPacket {

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(CoreBungee.get().getServerName(), out);
    }

    @Override
    public int getId() {
        return Packets.PROXY_DISCONNECT_PACKET;
    }

}
