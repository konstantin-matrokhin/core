package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.Arrays;
import java.util.List;

/**
 * Декодируем поток байтов во входящий пакет
 * Почитать о протоколе подробнее можно на гитхабе
 */
public final class PacketDecoder extends ByteToMessageDecoder {

    private final int MIN_PACKET_ID = 1; // Минимальный ID пакета

    private PacketResolver packetResolver; // Обрабатывает ID пакетов

    public PacketDecoder(PacketResolver packetResolver) {
        this.packetResolver = packetResolver;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext,
                          ByteBuf byteBuf, List<Object> list) throws Exception {
        byteBuf.markReaderIndex();

        ByteBuf prefix = byteBuf.readBytes(5);
        byte[] prefixBytes;

        if (prefix.hasArray()) {
            prefixBytes = prefix.array();
        } else {
            prefixBytes = new byte[prefix.readableBytes()];
            prefix.getBytes(prefix.readerIndex(), prefixBytes);
        }

        // Проверяем префикс
        if (Arrays.equals(prefixBytes, ProtocolCommons.PREFIX)) {

            // Валидный ли айди пакета
            byte id = byteBuf.readByte();
            if (id >= MIN_PACKET_ID) {
                int key = byteBuf.readInt();
                // Находим пакет по ID
                PacketIn p = packetResolver.getPacketIn(id);
                // Проверяем есть ли такой ID и проверяем длину пакета
                if (p != null) {
                    if (key != 0) {
                        p.setKey(key);
                        packetResolver.addToResponses(p, key);
                    }
                    System.out.println(String.format("-> %s, NAME: %s | key: %d",
                            p.getId(), p.getClass().getSimpleName(), key));

                    byteBuf.readerIndex(12);
                    p.read(byteBuf);
                    list.add(p);
                } else {
                    System.out.println("Ошибка! Неизвестный пакет [id = " + id + "]");
                }
            }
        }
        //Это очень важно!
        byteBuf.clear();
    }
}
