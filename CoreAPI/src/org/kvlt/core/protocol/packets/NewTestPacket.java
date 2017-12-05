package org.kvlt.core.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.Packet;
import org.kvlt.core.protocol.PacketUtil;

public class NewTestPacket extends Packet<Channel> {

    private int myNum;
    private String str;

    {
        myNum = 10;
    }

    @Override
    public void execute(Channel channel) {
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
