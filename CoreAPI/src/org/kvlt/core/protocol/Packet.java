package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public abstract class Packet<T> {

    private byte id;
    private byte length;

    public abstract void execute(T t);
    public abstract void readBytes(ByteBuf byteBuf);
    public abstract void writeBytes(ByteBuf byteBuf);
    public abstract void send(Destination dest, String name);

    public void send(Destination dest) {
        send(dest, "@all");
    }

    public void send(Channel channel) {
        channel.writeAndFlush(this, channel.voidPromise());
    }

    public void execute() {
        execute(null);
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
