package org.kvlt.core.packets;

import io.netty.buffer.ByteBuf;

public interface Packet {

    void execute(ByteBuf byteBuf);
    void writeBytes(ByteBuf byteBuf);
    byte getId();

    default void register() {
        Packets.register(getId(), this);
    }

}
