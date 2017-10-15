package org.kvlt.core;

import org.kvlt.core.entities.ServerPlayer;

import java.util.ArrayList;

public class ServerPlayers extends ArrayList<ServerPlayer> {

    private ArrayList<ServerPlayer> serverPlayers;

    public ServerPlayers() {
        serverPlayers = new ArrayList<>();
    }

    @Override
    public boolean add(ServerPlayer serverPlayer) {
        return serverPlayers.add(serverPlayer);
    }

    @Override
    public boolean remove(Object serverPlayer) {
        return serverPlayers.remove(serverPlayer);
    }

}
