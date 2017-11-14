package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

import java.io.Serializable;

public class PlayerProxyLoginPacket extends Packet implements Serializable {

    private OnlinePlayer player;
    private String proxy;

    public PlayerProxyLoginPacket(OnlinePlayer player, String proxy) {
        this.player = player;
        this.proxy = proxy;
    }

    @Override
    protected void onCore() {
        CoreServer.get().getProxies().getNode(proxy).getPlayers().add(player);
        CoreServer.get().getOnlinePlayers().add(player);

        Log.$(CoreServer.get().getProxies().getNode(proxy).getPlayers().get(0) + "");
        Log.$(CoreServer.get().getGameServers().getNode(proxy).getOnlinePlayers().get(0) + "");
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }

    public ServerPlayer getServerPlayer() {
        return player;
    }

}
