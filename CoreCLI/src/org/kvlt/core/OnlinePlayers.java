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
        return removeIf(p -> p.getName().equalsIgnoreCase(target.getName()));
    }

    @Override
    public boolean add(OnlinePlayer p) {
        if (contains(p)) return false;

        loadInfo(p);
        super.add(p);

        return true;
    }

    public void loadInfo(OnlinePlayer p) {
        try {
            int id = PlayerDB.initId(p.getName());
            p.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OnlinePlayer get(String name){
        return stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().get();
    }

}
