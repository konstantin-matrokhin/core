package org.kvlt.core.entities;

import java.util.HashSet;

public class PlayerList<T extends ServerPlayer> extends HashSet<T> {

    /**
     * Ищет по всему списку подходящего по имени игрока и удаляет его
     */
    @Override
    public boolean remove(Object o) {
        T target = (T) o;

        try {
            return removeIf(p -> p.getName().equalsIgnoreCase(target.getName()));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean contains(String name) {
        return stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null) != null;
    }

    @Override
    public boolean contains(Object o) {
        if (super.contains(o)) return true;

        T target = (T) o;
        T pc = stream().filter(p -> p.getName().equalsIgnoreCase(target.getName())).findFirst().orElse(null);

        return pc != null;
    }

    public T get(String name){
        try {
            return stream().filter(p ->
                    p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

}
