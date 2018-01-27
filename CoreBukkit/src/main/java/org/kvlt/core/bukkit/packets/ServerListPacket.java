package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class ServerListPacket implements PacketIn {

    private String[] serverList;

    @Override
    public void read(ByteBuf in) {
        serverList = PacketUtil.readStringArray(in);
    }

    @Override
    public void execute(Channel channel) {

    }

    public String[] getServerList() {
        return serverList;
    }

    @Override
    public int getId() {
        return Packets.SERVER_LIST_PACKET;
    }

}
