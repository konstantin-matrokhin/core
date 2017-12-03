package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.packets.PacketOld;

public class PlayerDisconnectPacket extends PacketOld {

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
