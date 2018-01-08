package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeeOutPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PremiumPlayerPacket extends BungeeOutPacket {

    private String player;

    public PremiumPlayerPacket(String player) {
        this.player = player;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player, out);
    }

    @Override
    public int getId() {
        return Packets.PREMIUM_PLAYER_PACKET;
    }
}
