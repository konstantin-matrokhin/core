package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

public class PacketFramer extends ByteToMessageCodec<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf out) throws Exception {
        int length = byteBuf.readableBytes();

        if (length > 0xfff) {
            System.out.println("error");
            return;
        }

        out.ensureWritable(length + 2);
        byteBuf.readBytes(out, length);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readable = byteBuf.readableBytes();

        if (readable < 8 || readable > 0xfff) {
            return;
        }

        System.out.println("\n");
        System.out.println(ByteBufUtil.prettyHexDump(byteBuf));

        byteBuf.markReaderIndex();

        int length = byteBuf.getShort(6);

        if (readable < length) {
            byteBuf.resetReaderIndex();
        } else {
            ByteBuf message = byteBuf.readBytes(length);
            list.add(message);
        }
        byteBuf.clear();
    }
}
