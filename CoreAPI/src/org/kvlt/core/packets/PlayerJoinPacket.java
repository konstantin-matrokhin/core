package org.kvlt.core.packets;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.ServerPlayer;

import java.io.Serializable;

public class PlayerJoinPacket extends Packet implements Serializable {

    private OnlinePlayer player;

    public PlayerJoinPacket(OnlinePlayer p) {
        this.player = p;
    }

    @Override
    public void onCore() {
        CoreServer.get().getOnlinePlayers().add(player);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

}
