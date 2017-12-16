package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Кодируем исходящий пакет в потом байтов
 */
public final class PacketEncoder extends MessageToByteEncoder<PacketOut> {

    /**
     * Байт, с которого начинаются произвольные данные
     */
    private final int DATA_PART_INDEX  = 0x8;

    /**
     * Байт, с которого записывается длина
     * т.к. записывается в short, то кол-во байт = 2
     */
    private final int SIZE_SHORT_INDEX = 0x6;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PacketOut packetOut, ByteBuf byteBuf) {
        int id = packetOut.getId();

        if (id <= 0 || id > Byte.MAX_VALUE) {
            throw new IllegalArgumentException("id must be in range [1; 127]");
        }

        byteBuf.writeBytes(ProtocolCommons.PREFIX); // записываем префикс
        byteBuf.writeByte(id); // и айди

        byteBuf.writerIndex(DATA_PART_INDEX); // перемещаем курсор для записи данных
        byteBuf.setShort(SIZE_SHORT_INDEX, (short) byteBuf.readableBytes()); // пишем в нужное место размер пакета

        packetOut.write(byteBuf); // пишем в пакет данные
    }
}
