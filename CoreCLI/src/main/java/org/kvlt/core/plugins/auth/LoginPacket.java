package org.kvlt.core.plugins.auth;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

public class LoginPacket implements PacketIn {

    private String playerName;
    private String password;

    @Override
    public void read(ByteBuf in) {
        playerName = PacketUtil.readString(in);
        password = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {

    }

    @Override
    public int getId() {
        return 0;
    }
}
