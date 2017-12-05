package org.kvlt.core.protocol.packets;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.protocol.Packet;

public class ProxyConnectPacket extends Packet {

    private String proxy;

    public ProxyConnectPacket() {}

    public ProxyConnectPacket(String proxy) {
        this.proxy = proxy;
    }

    @Override
    public void execute() {

    }

    @Override
    public void readBytes(ByteBuf byteBuf) {

    }

    @Override
    public void writeBytes(ByteBuf byteBuf) {

    }

}
