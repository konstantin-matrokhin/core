package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;

public class PlayerProxyQuitPacket extends Packet {

    private String playerName;

    public PlayerProxyQuitPacket(String playerName) {
        this.playerName = playerName;
    }

    @Override
    protected void onCore() {
        OnlinePlayer player = CoreServer.get().getOnlinePlayers().get(playerName);
        PlayerDB.save(player);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

}
