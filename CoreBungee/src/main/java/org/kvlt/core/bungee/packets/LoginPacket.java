package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.packets.protocol.PlayerPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class LoginPacket extends PlayerPacket {

    private String uuid;

    public LoginPacket(String playerName, String uuid) {
        setPlayerName(playerName);
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(getPlayerName(), out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_LOGIN_PACKET;
    }

}
