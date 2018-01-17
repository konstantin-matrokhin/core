package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class SwitchServerPacket extends BungeePacketOut {

    private String playerName;
    private String serverName;

    public SwitchServerPacket(String playerName, String serverName) {
        this.playerName = playerName;
        this.serverName = serverName;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(playerName, out);
        PacketUtil.writeString(serverName, out);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_SWITCH_SERVER_PACKET;
    }
}
