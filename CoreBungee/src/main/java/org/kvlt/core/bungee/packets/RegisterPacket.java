package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.PlayerPacket;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.PLAYER_REG_PACKET;

public class RegisterPacket extends PlayerPacket {

    private String password;

    public RegisterPacket(String playerName, String password) {
        setPlayerName(playerName);
        this.password = password;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(getPlayerName(), out);
        PacketUtil.writeString(password, out);
    }

    @Override
    public int getId() {
        return PLAYER_REG_PACKET;
    }
}
