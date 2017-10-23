package org.kvlt.core;

import org.kvlt.core.entities.OnlinePlayer;

import java.util.ArrayList;
import java.util.LinkedList;

public class OnlinePlayers extends ArrayList<OnlinePlayer> {

    /**
     * Ищет по всему списку подходящего по UUID и имени игрока и удаляет его
     */
    @Override
    public boolean remove(Object o) {
        OnlinePlayer target = (OnlinePlayer) o;
        for (OnlinePlayer p : this) {
            boolean sameName = p.getName().equalsIgnoreCase(target.getName());
            boolean sameUUID = p.getUUID().equals(target.getUUID());

            if (sameUUID) {
                if (sameName) {
                    super.remove(p);
                    return true;
                }
                break;
            }

        }
        return false;
    }

}
