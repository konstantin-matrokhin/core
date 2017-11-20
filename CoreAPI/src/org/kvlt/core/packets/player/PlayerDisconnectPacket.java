package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.packets.Packet;

public class PlayerDisconnectPacket extends Packet {

    private String playerName;

    @Override
    protected void onCore() {
        CoreServer.get().getOnlinePlayers().remove(playerName);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }
}
