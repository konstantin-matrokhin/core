package org.kvlt.core.events.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

public class PlayerLoginPacket implements PacketIn {

    private String playerName;
    private String proxyName;

    @Override
    public void read(ByteBuf in) {
        playerName = PacketUtil.readString(in);
        proxyName = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        
    }

    @Override
    public int getId() {
        return 9;
    }
}
