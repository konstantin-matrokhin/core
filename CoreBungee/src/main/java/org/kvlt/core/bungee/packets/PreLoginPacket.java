package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.Core;
import org.kvlt.core.bungee.packets.protocol.PlayerPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PreLoginPacket extends PlayerPacket {

    private String ip;

    public PreLoginPacket(String playerName, String ip) {
        setPlayerName(playerName);
        this.ip = ip;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(getPlayerName(), out);
        PacketUtil.writeString(ip, out);
        PacketUtil.writeString(Core.getAPI().getProxyName(), out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_PRELOGIN_PACKET;
    }
}
