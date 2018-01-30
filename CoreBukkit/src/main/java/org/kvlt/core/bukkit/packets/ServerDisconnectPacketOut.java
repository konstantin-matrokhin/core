package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class ServerDisconnectPacketOut extends BukkitPacketOut {

    private String name;

    public ServerDisconnectPacketOut(String name) {
        this.name = name;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(name, out);
    }

    @Override
    public int getId() {
        return Packets.SERVER_DISCONNECT_PACKET;
    }
}
