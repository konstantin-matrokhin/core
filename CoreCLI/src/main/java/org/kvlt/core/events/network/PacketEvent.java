package org.kvlt.core.events.network;

import io.netty.channel.Channel;
import org.kvlt.core.events.CoreEvent;
import org.kvlt.core.protocol.PacketIn;

public class PacketEvent implements CoreEvent {

    private int id;
    private PacketIn packet;
    private Channel channel;

    public PacketEvent(PacketIn packetIn, Channel channel) {
        this.id = packetIn.getId();
        this.packet = packetIn;
        this.channel = channel;
    }

    public int getId() {
        return id;
    }

    public PacketIn getPacket() {
        return packet;
    }


    public Channel getChannel() {
        return channel;
    }
}
