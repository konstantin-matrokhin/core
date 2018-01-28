package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.bukkit.Bukkit;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class BroadcastPacket implements PacketIn {

    private String message;

    @Override
    public void read(ByteBuf in) {
        message = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        Bukkit.broadcastMessage(message);
    }

    @Override
    public int getId() {
        return Packets.BROADCAST_PACKET;
    }

}
