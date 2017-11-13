package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;

public class PlayerDisconnectPacket extends Packet {

    private OnlinePlayer onlinePlayer;

    @Override
    protected void onCore() {
        CoreServer.get().getOnlinePlayers().remove(onlinePlayer);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }
}
