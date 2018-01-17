package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class EmailVerifyPacket extends BungeePacketOut {

    private String player;
    private String code;

    public EmailVerifyPacket(String player, String code) {
        this.player = player;
        this.code = code;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player, out);
        PacketUtil.writeString(code, out);
    }

    @Override
    public int getId() {
        return Packets.EMAIL_VERIFY_PACKET;
    }

}
