package org.kvlt.core.protocol;

/**
 * Основной интерфейс для пакетов
 */
public interface Packet {

    /**
     * При переопределении метода верните ID пакета
     * @return ID пакета
     */
    int getId();

}
