package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeeOutPacket;
import org.kvlt.core.protocol.PacketUtil;

public class SwitchServerPacket extends BungeeOutPacket {

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
        return 2;
    }
}
