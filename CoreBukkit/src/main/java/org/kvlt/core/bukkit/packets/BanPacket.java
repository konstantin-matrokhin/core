package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class BanPacket extends BukkitPacketOut {

    private String enforcer;
    private String victim;
    private String time;
    private String reason;

    public BanPacket(String enforcer, String victim, String time, String reason) {
        this.enforcer = enforcer;
        this.victim = victim;
        this.time = time;
        this.reason = reason;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(enforcer, out);
        PacketUtil.writeString(victim, out);
        PacketUtil.writeString(time, out);
        PacketUtil.writeString(reason, out);
    }

    @Override
    public int getId() {
        return Packets.BAN_PACKET;
    }

}
