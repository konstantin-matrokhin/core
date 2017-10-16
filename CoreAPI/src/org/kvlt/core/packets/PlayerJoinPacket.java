package org.kvlt.core.packets;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.ServerPlayer;

import java.io.Serializable;

public class PlayerJoinPacket extends Packet implements Serializable {

    private ServerPlayer player;
    private String server;

    public PlayerJoinPacket(ServerPlayer p, String server) {
        this.player = p;
        this.server = server;
    }

    @Override
    public void onCore() {
        if (CoreServer.get().getServerPlayers().contains(player)) {

        }
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

}
