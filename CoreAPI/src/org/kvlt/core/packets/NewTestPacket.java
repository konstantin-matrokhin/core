package org.kvlt.core.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

public class NewTestPacket extends Packet {

    private int myNum;

    {
        myNum = 10;
        setId(0x01);
    }

    @Override
    public void execute() {
        System.out.println(myNum);
    }

    @Override
    public void writeBytes(ByteBuf byteBuf) {
        System.out.println("called!");
        byteBuf.writeByte(getId());
    }

    @Override
    public void readBytes(ByteBuf byteBuf) {
        this.myNum = byteBuf.readInt();
    }

}
