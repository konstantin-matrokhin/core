package org.kvlt.core.packets;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;

public class PlayerInfoUpdatePacket extends Packet {

    public PlayerInfoUpdatePacket(OnlinePlayer op) {
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
