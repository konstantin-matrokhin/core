package org.kvlt.core.packets;

import io.netty.channel.Channel;
import org.kvlt.core.nodes.Node;
import org.kvlt.core.nodes.NodeContainer;
import org.kvlt.core.protocol.PacketOut;
import org.kvlt.core.utils.Finder;

public abstract class CorePacketOut implements PacketOut {

    public void send(Destination dest, String nodeName) {
        NodeContainer<? extends Node> node;
        if (dest == null || dest.equals(Destination.BUKKIT)) {
            node = Finder.getGameServers(nodeName);
        } else {
            node = Finder.getProxies(nodeName);
        }

        node.send(this);
    }

    @Override
    public void send() {}

    public void send(Destination dest) {
        send(dest, "@all");
    }

    public void send(Channel channel) {
        channel.writeAndFlush(this, channel.voidPromise());
    }

}
