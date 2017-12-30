package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeeOutPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PasswordRecoveryPacket extends BungeeOutPacket {

    private String playerName;

    public PasswordRecoveryPacket(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(playerName, out);
    }

    @Override
    public int getId() {
        return Packets.PASSWORD_RECOVERY_PACKET;
    }

}
