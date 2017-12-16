package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * Входящий пакет
 */
public interface PacketIn extends Packet {

    void read(ByteBuf in);
    void execute(Channel channel);

}
