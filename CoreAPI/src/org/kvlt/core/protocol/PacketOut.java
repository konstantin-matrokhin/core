package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.nodes.Node;
import org.kvlt.core.nodes.NodeContainer;
import org.kvlt.core.utils.Finder;

public abstract class PacketOut implements Packet {

    public abstract void write(ByteBuf out);

    public abstract void send();

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
