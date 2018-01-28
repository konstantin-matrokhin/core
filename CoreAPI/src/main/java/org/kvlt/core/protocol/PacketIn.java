package org.kvlt.core.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * Входящий пакет
 */
public interface PacketIn extends Packet {

    /**
     * Тут читаем данные из буфера.
     * Для чтения строк и тп. используйте класс <b>PacketUtil</b>
     * @param in сам буфер
     */
    void read(ByteBuf in);

    /**
     * Метод выполняется после чтения пакета (<b>read(ByteBuf in)</b>)
     * @param channel является каналом с которого пришел пакет. Можно его отправить обратно по каналу, например
     */
    void execute(Channel channel);

}
