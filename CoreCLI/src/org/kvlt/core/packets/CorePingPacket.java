package org.kvlt.core.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.CoreServer;
import org.kvlt.core.protocol.PacketOut;
import org.kvlt.core.protocol.PacketUtil;

public class CorePingPacket implements PacketOut {

    @Override
    public void write(ByteBuf out) {
        PacketUtil.writeString("hello", out);
    }

    @Override
    public void send() {
        CoreServer.get().getProxies().send(this);
    }

    @Override
    public int getId() {
        return 1;
    }
}
