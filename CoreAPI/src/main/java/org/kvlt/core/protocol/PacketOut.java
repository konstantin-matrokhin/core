package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;

public interface PacketOut extends Packet {

     void write(ByteBuf out);
     void send();

}
