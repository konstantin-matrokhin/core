package net.enot.dartasystem.packets;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by Енот on 18.05.2017.
 */
public class TestPacket implements Packet {

    public TestPacket(){}

    @Override
    public void read(ByteBuf byteBuf) throws IOException {
        System.out.println(byteBuf.readInt());
        //byteBuf.writeInt(200);
    }

    @Override
    public void write(ByteBuf byteBuf) throws IOException{
        byteBuf.writeInt(100);
        //System.out.println(byteBuf.readInt());
    }
}
