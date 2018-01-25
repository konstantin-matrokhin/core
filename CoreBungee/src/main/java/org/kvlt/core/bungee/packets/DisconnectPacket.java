package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.Core;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class DisconnectPacket extends BungeePacketOut {

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(Core.getAPI().getProxyName(), out);
    }

    @Override
    public int getId() {
        return Packets.PROXY_DISCONNECT_PACKET;
    }

}
