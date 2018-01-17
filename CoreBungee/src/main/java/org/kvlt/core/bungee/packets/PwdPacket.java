package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PwdPacket extends BungeePacketOut {

    private String player;
    private String oldPassword;
    private String newPassword;

    public PwdPacket(String player, String oldPassword, String newPassword) {
        this.player = player;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player, out);
        PacketUtil.writeString(oldPassword, out);
        PacketUtil.writeString(newPassword, out);
    }

    @Override
    public int getId() {
        return Packets.PWD_PACKET;
    }

}
