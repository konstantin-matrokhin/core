package org.kvlt.core.packets.proxy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

public class ProxyDisconnectPacket implements PacketIn {

    private String name;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        System.out.println(String.format("Прокси-сервер отсоединен (%s)", name));
        Proxy proxy = CoreServer.get().getProxies().getNode(name);
        CoreServer.get().getProxies().removeNode(proxy);
        channel.disconnect();
    }

    @Override
    public int getId() {
        return 4;
    }
}
