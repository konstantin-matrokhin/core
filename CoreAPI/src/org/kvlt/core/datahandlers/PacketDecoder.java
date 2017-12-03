package org.kvlt.core.datahandlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.packets.Packets;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        Channel channel = ctx.channel();

        byte packetId = byteBuf.readByte();
        Packet packet = Packets.getById(packetId);
        packet.execute(byteBuf);

    }
}