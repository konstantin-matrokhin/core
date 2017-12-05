package org.kvlt.core.packets;

import io.netty.buffer.ByteBuf;

public abstract class Packet {

    private byte id;
    private byte length;

    protected abstract void writeBytes(ByteBuf byteBuf);

    public abstract void readBytes(ByteBuf byteBuf);
    public abstract void execute();

    public void fillBytes(ByteBuf byteBuf) {
        byteBuf.writerIndex(5);
        writeBytes(byteBuf);
        byteBuf.resetWriterIndex();
        byteBuf.writeByte(getId());
        byteBuf.writeInt(byteBuf.readableBytes());
    }

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
