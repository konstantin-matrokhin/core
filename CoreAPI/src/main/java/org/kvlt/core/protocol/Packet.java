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

    default int getKey() {
        return 0;
    }

    default void setKey(int key) {

    }

}
