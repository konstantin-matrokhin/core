package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class PacketDecoder extends ByteToMessageDecoder {

    private final int MIN_BYTES        = 8;
    private final int MAX_BYTES        = 128;
    private final int MIN_PACKET_ID    = 1;

    private PacketResolver packetResolver;

    public PacketDecoder(PacketResolver packetResolver) {
        this.packetResolver = packetResolver;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readable = byteBuf.readableBytes();

        if (readable < MIN_BYTES || readable > MAX_BYTES) {
            return;
        }

        byte[] receivedPrefix = ByteBufUtil.getBytes(byteBuf.readBytes(5));
        if (Arrays.equals(receivedPrefix, ProtocolCommons.PREFIX)) {

            byte id = byteBuf.readByte();
            if (id >= MIN_PACKET_ID) {

                short length = byteBuf.readShort();
                if (length >= MIN_BYTES) {

                    PacketIn p = packetResolver.getPacketIn(id);
                    if (p != null && readable == length) {
                        p.read(byteBuf);
                        list.add(p);
                    } else {
                        throw new IOException("Invalid packet with [id = " + id + "]");
                    }

                }
            }
        }
    }
}
