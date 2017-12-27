package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeeOutPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class EmailAddPacket extends BungeeOutPacket {

    private String player;
    private String email;

    public EmailAddPacket(String player, String email) {
        this.player = player;
        this.email = email;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player, out);
        PacketUtil.writeString(email, out);
    }

    @Override
    public int getId() {
        return Packets.EMAIL_ADD_PACKET;
    }

}
