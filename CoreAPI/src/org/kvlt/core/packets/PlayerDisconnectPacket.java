package org.kvlt.core.packets;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;

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
