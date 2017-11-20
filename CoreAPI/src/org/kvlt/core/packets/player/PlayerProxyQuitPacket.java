package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.packets.Packet;

public class PlayerProxyQuitPacket extends Packet {

    private OnlinePlayer player;

    public PlayerProxyQuitPacket(OnlinePlayer player) {
        this.player = player;
    }

    @Override
    protected void onCore() {
        OnlinePlayer realPlayer = CoreServer.get().getOnlinePlayers().get(player.getName());
        PlayedTimeCounter.stop(realPlayer);
        PlayerDB.save(realPlayer);
        CoreServer.get().getOnlinePlayers().remove(realPlayer);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

}
