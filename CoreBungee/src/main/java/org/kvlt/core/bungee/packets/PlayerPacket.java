package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.bungee.packets.protocol.BungeePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerPacket extends BungeePacketOut {

    private ProxiedPlayer player;

    public PlayerPacket(ProxiedPlayer player) {
        this.player = player;
    }

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString(player.getName(), out);
        PacketUtil.writeString(player.getAddress().getHostString(), out);
        PacketUtil.writeString(player.getServer().getInfo().getName(), out);
        out.writeLong(System.currentTimeMillis());
    }

    @Override
    public int getId() {
        return Packets.PLAYER_PACKET;
    }
}
