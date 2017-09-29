package net.enot.dartasystem.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.io.IOException;
import java.util.List;

/**
 * Created by Енот on 30.04.2017.
 */
public class FrameHandler extends ByteToMessageCodec<ByteBuf> {

    //TODO Переписать, когда буду лучше знать.

    private static final int MAXIMUM_MESSAGE_LENGTH = 0xFFFF;

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        // length of this message
        int length = msg.readableBytes();
        if (length > MAXIMUM_MESSAGE_LENGTH) {
            throw new IOException("Message too long: " + length + " bytes");
        }
        out.ensureWritable(2 + length);

        // write
        out.writeShort(length);
        msg.readBytes(out, length);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        if (msg.readableBytes() < 2) {
            // no (full) length header received yet, wait
            return;
        }

        // mark so we can reset here if the message isn't complete yet
        msg.markReaderIndex();
        int messageLength = msg.readUnsignedShort();
        if (messageLength > msg.readableBytes()) {
            // length header received but message not yet complete, wait
            msg.resetReaderIndex();
            return;
        }

        // message complete, queue for handling
        ByteBuf message = msg.readBytes(messageLength);
        out.add(message);
    }
}
