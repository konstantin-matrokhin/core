package org.kvlt.core.bukkit.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.json.JsonPacket;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class ServerListPacket implements PacketIn, JsonPacket {

    private String json;

    @Override
    public void read(ByteBuf in) {
        json = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        System.out.println(json);
    }

    @Override
    public int getId() {
        return Packets.SERVER_LIST_PACKET;
    }

    @Override
    public String getJson() {
        return json;
    }

}
