package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;

public class ConnectPacket extends BukkitPacketOut {

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
        return 3;
    }

}
