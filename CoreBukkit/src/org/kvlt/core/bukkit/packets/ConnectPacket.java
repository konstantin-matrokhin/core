package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;

public class ConnectPacket extends BukkitPacketOut {

    private String name;
    private short port;

    public ConnectPacket(String name, short port) {
        this.name = name;
        this.port = port;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(name, out);
        out.writeShort(port);
    }

    @Override
    public int getId() {
        return 3;
    }

}
