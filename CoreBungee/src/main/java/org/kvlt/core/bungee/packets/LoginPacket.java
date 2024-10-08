package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class LoginPacket extends BungeePacketOut {

    private String name;

    public LoginPacket(String name) {
        this.name = name;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(name, out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_LOGIN_PACKET;
    }

}
