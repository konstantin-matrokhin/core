package org.kvlt.core.entities;

import java.util.HashSet;

/**
 * Список игроков
 * @param <T> должен содержать класс игроков из CoreAPI
 */
public class PlayerList<T extends ServerPlayer> extends HashSet<T> {

    public boolean remove(String name) {
        return remove(get(name));
    }

    public boolean contains(String name) {
        return contains(get(name));
    }

    /**
     * @param name принимает имя игрока
     * @return объект игрока из списка
     */
    public T get(String name){
        return stream().filter(p ->
                p.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

}
