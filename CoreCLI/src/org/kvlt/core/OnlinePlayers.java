package org.kvlt.core;

import org.kvlt.core.db.PlayerDB;
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
        OnlinePlayer player = null;

        try {
            player = stream().filter(p -> p.getName().equalsIgnoreCase(target.getName())).findFirst().get();
            PlayerDB.save(player);
            return removeIf(p -> p.getName().equalsIgnoreCase(target.getName()));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean add(OnlinePlayer p) {
        if (contains(p)) return false;
        super.add(p);
        return true;
    }

    public OnlinePlayer get(String name){
        try {
            return stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

}
