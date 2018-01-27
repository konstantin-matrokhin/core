package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.packets.protocol.PlayerPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerLoginPacket extends PlayerPacket {

    private String ip;

    public PlayerLoginPacket(String playerName, String ip) {
        setPlayerName(playerName);
        this.ip = ip;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(getPlayerName(), out);
        PacketUtil.writeString(ip, out);
        PacketUtil.writeString(CoreBungee.getAPI().getProxyName(), out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_LOGIN_PACKET;
    }
}
