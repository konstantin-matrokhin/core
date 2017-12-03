package org.kvlt.core.packets;

import io.netty.buffer.ByteBuf;

public class NewTestPacket implements Packet {

    private int num;

    @Override
    public void execute(ByteBuf byteBuf) {
        num = byteBuf.readInt();
        System.out.println(num);
    }

    @Override
    public void writeBytes(ByteBuf byteBuf) {

    }

    @Override
    public byte getId() {
        return 0x01;
    }
}
