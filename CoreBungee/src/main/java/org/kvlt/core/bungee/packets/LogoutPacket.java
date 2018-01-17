package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class LogoutPacket extends BungeePacketOut {

    private String player;

    public LogoutPacket(String player) {
        this.player = player;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player, out);
    }

    @Override
    public int getId() {
        return Packets.LOGOUT_PACKET;
    }
}
