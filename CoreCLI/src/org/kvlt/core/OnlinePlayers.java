package org.kvlt.core;

import org.kvlt.core.entities.OnlinePlayer;

import java.util.LinkedList;

public class OnlinePlayers extends LinkedList<OnlinePlayer> {

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
