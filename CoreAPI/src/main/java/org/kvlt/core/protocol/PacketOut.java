package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;

/**
 * Исходящий пакет
 */
public interface PacketOut extends Packet {

    void write(ByteBuf out);
    void send();
}
