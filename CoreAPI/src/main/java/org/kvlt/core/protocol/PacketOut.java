package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * Исходящий пакет
 */
public interface PacketOut extends Packet {

    void write(ByteBuf out);
    void send(Channel channel);
}
