package org.kvlt.core.packets;

import io.netty.buffer.ByteBuf;

public class NewTestPacket extends Packet {

    private int myNum;
    private String str;

    {
        myNum = 10;
    }

    @Override
    public void execute() {
        System.out.println(myNum);
        System.out.println(str);
    }

    @Override
    public void writeBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(myNum);
        PacketUtil.writeString("kek", byteBuf);
        PacketUtil.writeString("2kek2", byteBuf);
    }

    @Override
    public void readBytes(ByteBuf byteBuf) {
        this.myNum = byteBuf.readInt();
        this.str = PacketUtil.readString(byteBuf) + " | " + PacketUtil.readString(byteBuf);
    }

}
