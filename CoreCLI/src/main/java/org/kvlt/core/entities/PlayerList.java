package org.kvlt.core.entities;

import io.netty.util.internal.ConcurrentSet;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Список игроков
 * @param <T> должен содержать класс игроков из CoreAPI
 */
public class PlayerList<T extends ServerPlayer> implements Iterable<T> {

    private Set<T> players;

    {
        players = new ConcurrentSet<>();
    }

    public boolean add(T player) {
        return players.add(player);
    }

    public boolean remove(ServerPlayer player) {
        return players.remove(player);
    }

    public boolean contains(ServerPlayer player) {
        return players.remove(player);
    }

    public boolean remove(String name) {
        return players.remove(get(name));
    }

    public boolean contains(String name) {
        return players.contains(get(name));
    }

    /**
     * @param name принимает имя игрока
     * @return объект игрока из списка
     */
    public T get(String name){
        return players.stream().filter(p ->
                p.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public int size() {
        return players.size();
    }

    @Override
    public Iterator<T> iterator() {
        return players.iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        players.forEach(action);
    }

    public Stream<T> stream() {
        return players.stream();
    }
}
