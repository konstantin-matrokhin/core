package net.enot.dartasystem.packets;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Енот on 23.04.2017.
 */
public interface Packet {

    void read(ByteBuf byteBuf) throws IOException;
    void write(ByteBuf byteBuf) throws IOException;

}
