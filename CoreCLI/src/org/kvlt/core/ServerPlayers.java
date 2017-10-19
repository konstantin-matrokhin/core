package org.kvlt.core;

import org.kvlt.core.db.DB;
import org.kvlt.core.entities.ServerPlayer;

import java.util.LinkedHashSet;

public class ServerPlayers extends LinkedHashSet<ServerPlayer> {

    @Override
    public boolean add(ServerPlayer p) {
        if (contains(p)) return false;

        try {
            DB.getPlayerDB().addPlayer(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
