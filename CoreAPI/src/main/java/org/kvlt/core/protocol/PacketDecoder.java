package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Декодируем поток байтов во входящий пакет
 * Почитать о протоколе подробнее можно на гитхабе
 */
public final class PacketDecoder extends ByteToMessageDecoder {

    private final int MIN_BYTES        = 0x8; // Минимальное кол-во байтов для валидного пакета
    private final int MAX_BYTES        = 0xffff; // Максимальное кол-во байт - 65535
    private final int MIN_PACKET_ID    = 0x1; // Минимальный ID пакета

    private PacketResolver packetResolver; // Обрабатывает ID пакетов

    public PacketDecoder(PacketResolver packetResolver) {
        this.packetResolver = packetResolver;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readable = byteBuf.readableBytes();

        // Смотрим кол-во байт
        if (readable < MIN_BYTES || readable > MAX_BYTES) {
            return;
        }

        // Проверяем префикс
        byte[] receivedPrefix = ByteBufUtil.getBytes(byteBuf.readBytes(5));
        if (Arrays.equals(receivedPrefix, ProtocolCommons.PREFIX)) {

            System.out.println("Пришел префикс");

            // Валидный ли айди пакета
            byte id = byteBuf.readByte();
            if (id >= MIN_PACKET_ID) {

                System.out.println("id = " + id);

                // Читаем длину пакета
                short length = byteBuf.readShort();
                if (length >= MIN_BYTES && readable == length) {

                    System.out.println("Принято " + readable);

                    // Находим пакет по ID
                    PacketIn p = packetResolver.getPacketIn(id);
                    // Проверяем есть ли такой ID и проверяем длину пакета
                    if (p != null) {
                        p.read(byteBuf);
                        list.add(p);
                    } else {
                        System.out.println("Invalid packet with [id = " + id + "]");
                    }

                }
            }
        }
    }
}
