package org.kvlt.core.entities;

/**
 * Пустой игрок. Класс служит для создания обычных игроков, т.к. класс ServerPlayer абстрактный.
 */
public class SimplePlayer extends ServerPlayer {

    public SimplePlayer() {}

    public SimplePlayer(String name) {
        setName(name);
    }

}
