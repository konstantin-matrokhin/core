package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

import java.io.Serializable;

public class PlayerJoinPacket extends Packet implements Serializable {

    private OnlinePlayer player;

    public PlayerJoinPacket(OnlinePlayer p) {
        this.player = p;
    }

    @Override
    public void onCore() {
        //TODO: make loading
        PlayerDB.loadOnlinePlayer(player);
        CoreServer.get().getOnlinePlayers().add(player);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

}
