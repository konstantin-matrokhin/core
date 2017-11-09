package org.kvlt.core;

import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.utils.Log;

import java.util.HashSet;

public class ServerPlayers extends HashSet<ServerPlayer> {

    /**
     * Добавляет и в спиком и в БД игрока
     */
    @Override
    public boolean add(ServerPlayer p) {
        if (contains(p)) return false;

        try {
            int id = PlayerDB.initId(p.getName());
            p.setId(id);
            super.add(p);
            Log.$(id + " | " + p.getName() + " joined.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
