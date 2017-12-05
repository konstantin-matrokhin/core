package org.kvlt.core.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

public abstract class Packet {

    private byte id;
    private byte length;

    public abstract void writeBytes(ByteBuf byteBuf);
    public abstract void readBytes(ByteBuf byteBuf);
    public abstract void execute();

    public byte getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = (byte) id;
    }

    public byte getLength() {
        return length;
    }

    protected void setLength(int length) {
        this.length = (byte) length;
    }

}
