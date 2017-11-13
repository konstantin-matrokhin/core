package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;
import org.kvlt.core.entities.OnlinePlayer;

public class PlayerQuitServerPacket extends Packet {

    private OnlinePlayer player;

    public PlayerQuitServerPacket(OnlinePlayer player) {
        this.player = player;
    }

    @Override
    protected void onCore() {
        CoreServer.get().getOnlinePlayers().remove(player);
        Log.$("disconnect " + player.getName());
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

}
