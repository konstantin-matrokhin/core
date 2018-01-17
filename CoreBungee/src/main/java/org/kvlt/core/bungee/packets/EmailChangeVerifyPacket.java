package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class EmailChangeVerifyPacket extends BungeePacketOut {

    private String player;
    private String code1;
    private String code2;

    public EmailChangeVerifyPacket(String player, String code1, String code2) {
        this.player = player;
        this.code1 = code1;
        this.code2 = code2;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player, out);
        PacketUtil.writeString(code1, out);
        PacketUtil.writeString(code2, out);
    }

    @Override
    public int getId() {
        return Packets.EMAIL_CHANGE_VERIFY_PACKET;
    }

}
