package org.kvlt.core.datahandlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.packets.Packets;

import java.util.List;

public class PacketFramer extends ByteToMessageCodec<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) {
        packet.fillBytes(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        byte id;
        int length;
        int readable = byteBuf.readableBytes();

        if (readable < 2 || readable > Byte.MAX_VALUE) {
            return;
        }

        id = byteBuf.readByte();
        if (id == 0) return;

        length = byteBuf.readInt();
        if (length < 1) return;

        Packet p = Packets.getById(id);
        System.out.println(id + " | " + length);
        if (readable == length) {
            p.readBytes(byteBuf);
        }

        list.add(p);
    }
}
