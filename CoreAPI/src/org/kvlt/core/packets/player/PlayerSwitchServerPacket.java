package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

public class PlayerSwitchServerPacket extends Packet {

    private String playerName;
    private String to;

    public PlayerSwitchServerPacket(String playerName, String to) {
        this.playerName = playerName;
        this.to = to;
    }

    @Override
    protected void onCore() {
        OnlinePlayer p = CoreServer.get().getOnlinePlayers().get(playerName);
        p.setCurrentServer(CoreServer.get().getGameServers().getNode(to));

        Log.$(playerName + " сменил сервер с " + p.getCurrentServer().getName() + " на " + to);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

}
