package org.kvlt.core;

import org.kvlt.core.entities.OnlinePlayer;

import java.util.ArrayList;
import java.util.LinkedList;

public class OnlinePlayers extends ArrayList<OnlinePlayer> {

    /**
     * Ищет по всему списку подходящего по имени игрока и удаляет его
     */
    @Override
    public boolean remove(Object o) {
        OnlinePlayer target = (OnlinePlayer) o;
        return removeIf(p -> p.getName().equalsIgnoreCase(target.getName()));
    }

}
