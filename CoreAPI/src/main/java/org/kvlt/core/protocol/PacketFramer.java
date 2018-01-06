package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.io.IOException;
import java.util.List;

public class PacketFramer extends ByteToMessageCodec<ByteBuf> {

    private static final int MAX_BYTES = 65_535;
    private static final int MIN_BYTES = 8;
    private static final int LENGTH_INDEX = 6;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ByteBuf out) throws Exception {
        int length = byteBuf.readableBytes();

        if (length > MAX_BYTES) {
            throw new IOException("Слишком длинный пакет!");
        }

        out.ensureWritable(length);
        byteBuf.readBytes(out, length);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        int readable = byteBuf.readableBytes();

        if (readable < MIN_BYTES || readable > MAX_BYTES) {
            return;
        }

        byteBuf.markReaderIndex();

        int length = byteBuf.getShort(LENGTH_INDEX);
        if (readable < length) {
            byteBuf.resetReaderIndex();
        } else {
            ByteBuf message = byteBuf.readBytes(length);
            list.add(message);
        }

        byteBuf.clear();
    }
}
