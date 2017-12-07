package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<PacketOut> {

    private final int DATA_PART_INDEX  = 8;
    private final int SIZE_SHORT_INDEX = 6;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PacketOut packetOut, ByteBuf byteBuf) {
        int id = packetOut.getId();

        if (id <= 0) {
            throw new IllegalArgumentException("id must be positive");
        }

        byteBuf.writeBytes(ProtocolCommons.PREFIX);
        byteBuf.writeByte(id);

        byteBuf.writerIndex(DATA_PART_INDEX);

        packetOut.write(byteBuf);

        byteBuf.setShort(SIZE_SHORT_INDEX, (short) byteBuf.readableBytes());
    }
}
