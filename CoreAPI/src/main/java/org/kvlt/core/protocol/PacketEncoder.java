package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Кодируем исходящий пакет в потом байтов
 */
public final class PacketEncoder extends MessageToByteEncoder<PacketOut> {

    /**
     * Байт, с которого записывается длина
     * т.к. записывается в short, то кол-во байт = 2
     */
    private final int SIZE_SHORT_INDEX = 10;

    /**
     * Байт, с которого начинаются произвольные данные
     */
    private final int DATA_PART_INDEX  = SIZE_SHORT_INDEX + 2;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, PacketOut packetOut, ByteBuf byteBuf) {
        int id = packetOut.getId();
        int key = packetOut.getKey();

        if (id <= 0 || id > Byte.MAX_VALUE) {
            throw new IllegalArgumentException("id must be in range [1; 127]");
        }

        byteBuf.writeBytes(ProtocolCommons.PREFIX); // записываем префикс
        byteBuf.writeByte(id); // и айди
        byteBuf.writeInt(key);
        byteBuf.writerIndex(DATA_PART_INDEX); // перемещаем курсор для записи данных
        packetOut.write(byteBuf); // пишем в пакет данные
        byteBuf.setShort(SIZE_SHORT_INDEX, (short) byteBuf.readableBytes()); // пишем в нужное место размер пакета

        System.out.println(String.format("<- ID: %d, NAME: %s, KEY: %s", id, packetOut.getClass().getSimpleName(), key));
    }
}
