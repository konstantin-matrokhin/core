package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;

public abstract class Packet {

    private byte id;
    private byte length;

    public abstract void execute();
    public abstract void readBytes(ByteBuf byteBuf);
    public abstract void writeBytes(ByteBuf byteBuf);

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
