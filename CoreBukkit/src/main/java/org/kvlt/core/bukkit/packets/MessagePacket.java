package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class MessagePacket implements PacketIn {

    private String recipient;
    private String message;

    @Override
    public void read(ByteBuf in) {
        recipient = PacketUtil.readString(in);
        message = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        Player p = Bukkit.getPlayer(recipient);

        if (p != null) {
            p.sendMessage(message);
        }
    }

    @Override
    public int getId() {
        return Packets.MESSAGE_PACKET;
    }

}
