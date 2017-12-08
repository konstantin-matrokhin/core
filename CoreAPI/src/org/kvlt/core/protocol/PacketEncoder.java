package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public final class PacketEncoder extends MessageToByteEncoder<PacketOut> {

    private final int DATA_PART_INDEX  = 0x8;
    private final int SIZE_SHORT_INDEX = 0x6;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PacketOut packetOut, ByteBuf byteBuf) {
        int id = packetOut.getId();

        if (id <= 0 || id > Byte.MAX_VALUE) {
            throw new IllegalArgumentException("id must be in range [1; 127]");
        }

        byteBuf.writeBytes(ProtocolCommons.PREFIX);
        byteBuf.writeByte(id);

        byteBuf.writerIndex(DATA_PART_INDEX);

        packetOut.write(byteBuf);

        byteBuf.setShort(SIZE_SHORT_INDEX, (short) byteBuf.readableBytes());
    }
}
