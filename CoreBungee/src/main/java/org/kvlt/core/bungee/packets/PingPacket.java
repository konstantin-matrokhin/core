package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

public class PingPacket implements PacketIn {

    private String result;

    @Override
    public void read(ByteBuf in) {
        result = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        System.out.println(result);
    }

    @Override
    public int getId() {
        return 2;
    }
}
