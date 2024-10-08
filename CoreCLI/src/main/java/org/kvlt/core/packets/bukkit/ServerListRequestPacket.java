package org.kvlt.core.packets.bukkit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class ServerListRequestPacket implements PacketIn {

    private String pattern;
    private int key;

    @Override
    public void read(ByteBuf in) {
        pattern = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ServerListPacket packet = new ServerListPacket(pattern);
        packet.setKey(key);
        packet.send(channel);
    }

    @Override
    public int getId() {
        return Packets.SERVER_LIST_PACKET;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

}
