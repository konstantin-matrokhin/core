package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class MutePacket extends CorePacketOut {

    private String victim;
    private long muteUntil;

    public MutePacket(String victim, long muteUntil) {
        this.victim = victim;
        this.muteUntil = muteUntil;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(victim, out);
        out.writeLong(muteUntil);
    }

    @Override
    public int getId() {
        return Packets.MUTE_PACKET;
    }

}
