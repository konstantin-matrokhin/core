package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class KickPacket extends CorePacketOut {

    private static final String DEFAULT_REASON = "Kicked!";

    private String targetName;
    private String reason;

    public KickPacket(String targetName, String reason) {
        this.targetName = targetName;
        this.reason = reason != null ? reason : DEFAULT_REASON;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(targetName, out);
        PacketUtil.writeString(reason, out);
    }

    @Override
    public void send() {
        send(Destination.BUNGEE);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_KICK_PACKET;
    }
}
