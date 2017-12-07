package org.kvlt.core.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

public class ProxyConnectPacket extends PacketIn {

    private String name;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute() {
        System.out.println("NAME IS: " + name);
    }

    @Override
    public int getId() {
        return 1;
    }

    public String getName() {
        return name;
    }

}
