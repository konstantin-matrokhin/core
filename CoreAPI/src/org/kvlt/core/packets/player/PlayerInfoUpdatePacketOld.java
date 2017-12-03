package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.PacketOld;

public class PlayerInfoUpdatePacketOld extends PacketOld {

    public PlayerInfoUpdatePacketOld(OnlinePlayer op) {
        OnlinePlayer player = CoreServer.get().getOnlinePlayers().get(op.getName());
        player.setCurrentServer(op.getCurrentServer());
        player.setIp(op.getIp());
        player.setLastIp(op.getLastIp());
    }

    @Override
    protected void onCore() {

    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

}
