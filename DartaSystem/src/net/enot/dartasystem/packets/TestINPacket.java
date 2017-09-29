package net.enot.dartasystem.packets;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Енот on 13.05.2017.
 */
public class TestINPacket implements Packet {

    public TestINPacket(){}

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        System.out.println(byteBuf.readInt());
        byteBuf.writeInt(20);
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException {
        byteBuf.writeInt(10);
        System.out.println(byteBuf.readInt());
    }
}
