package org.kvlt.core.protocol.packets;

import io.netty.channel.Channel;
import org.kvlt.core.nodes.Node;
import org.kvlt.core.nodes.NodeContainer;
import org.kvlt.core.protocol.PacketOut;
import org.kvlt.core.utils.Finder;

public abstract class CorePacketOut implements PacketOut {

    public void send(Destination dest, String nodeName) {
        NodeContainer<? extends Node> node = null;

        if (dest == null || dest.equals(Destination.BUKKIT)) {
            node = Finder.getGameServers(nodeName);
        } else if (dest.equals(Destination.BUNGEE)) {
            node = Finder.getProxies(nodeName);
        }

        node.send(this);
    }

    public void send(Destination dest) {
        send(dest, "@all");
    }

    public void send(Channel channel) {
        channel.writeAndFlush(this, channel.voidPromise());
    }

}
