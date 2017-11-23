package org.kvlt.core.nodes;

import io.netty.channel.Channel;
import org.kvlt.core.packets.Packet;

public interface Node {

    void send(Packet packet);
    void send(Packet... packets);
    void setName(String name);
    String getName();
    void setChannel(Channel channel);
    Channel getChannel();

}
