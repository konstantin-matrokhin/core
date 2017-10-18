package org.kvlt.core;

import org.kvlt.core.db.DB;
import org.kvlt.core.entities.ServerPlayer;

import java.util.ArrayList;

public class ServerPlayers extends ArrayList<ServerPlayer> {

    @Override
    public boolean add(ServerPlayer p) {
        try {
            DB.getPlayerDB().addPlayer(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
