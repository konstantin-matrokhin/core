package org.kvlt.core.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.protocol.Packet;
import org.kvlt.core.protocol.PacketUtil;

public class ProxyConnectPacket extends Packet<Channel> {

    private String proxy;

    public ProxyConnectPacket() {}

    public ProxyConnectPacket(String proxy) {
        this.proxy = proxy;
    }

    @Override
    public void execute(Channel channel) {
        String response = String.format("Прокси-сервер подключен (%s)", proxy);
        System.out.println(response);
        new Proxy(proxy, channel);
    }

    @Override
    public void readBytes(ByteBuf byteBuf) {
        this.proxy = PacketUtil.readString(byteBuf);
    }

    @Override
    public void writeBytes(ByteBuf byteBuf) {
        PacketUtil.writeString(proxy, byteBuf);
    }

}
