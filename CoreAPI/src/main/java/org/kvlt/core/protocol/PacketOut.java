package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;

/**
 * Исходящий пакет
 */
public interface PacketOut extends Packet {

    /**
     * Здесь необходимо записать все данные в пакет.
     * Для записи строк и массивов используйте класс <b>PacketUtil<b>
     * @param out буфер для записи
     */
    void write(ByteBuf out);

    /**
     * Для отправления пакета.
     * Обязательно определите этот метод!
     */
    void send();
}
