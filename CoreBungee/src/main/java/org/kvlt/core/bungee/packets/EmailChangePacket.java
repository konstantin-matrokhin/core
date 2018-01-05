package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeeOutPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class EmailChangePacket extends BungeeOutPacket {

    private String player;
    private String oldEmail;
    private String newEmail;

    public EmailChangePacket(String player, String oldEmail, String newEmail) {
        this.player = player;
        this.oldEmail = oldEmail;
        this.newEmail = newEmail;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player, out);
        PacketUtil.writeString(oldEmail, out);
        PacketUtil.writeString(newEmail, out);
    }

    @Override
    public int getId() {
        return Packets.EMAIL_CHANGE_PACKET;
    }

}
