package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class ServerListRequestPacket extends BukkitPacketOut {

    private String pattern;

    public ServerListRequestPacket(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(pattern, out);
    }

    @Override
    public int getId() {
        return Packets.SERVER_LIST_PACKET;
    }

}
