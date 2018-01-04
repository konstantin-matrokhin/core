package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeeOutPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class KickRequestPacket extends BungeeOutPacket {

    private String enforcer;
    private String victim;
    private String reason;

    public KickRequestPacket(String enforcer, String victim, String reason) {
        this.enforcer = enforcer;
        this.victim = victim;
        this.reason = reason;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(enforcer, out);
        PacketUtil.writeString(victim, out);
        PacketUtil.writeString(reason, out);
    }

    @Override
    public int getId() {
        return Packets.KICK_PACKET;
    }

}
