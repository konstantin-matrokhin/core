package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

import java.io.Serializable;

public class PlayerProxyLoginPacket extends Packet implements Serializable {

    private OnlinePlayer player;
    private String proxy;
    private String server;

    public PlayerProxyLoginPacket(OnlinePlayer player, String proxy, String server) {
        this.player = player;
        this.proxy = proxy;
        this.server = server;
    }

    @Override
    protected void onCore() {
        CoreServer.get().getProxies().getNode(proxy).getPlayers().add(player);
        PlayerDB.loadOnlinePlayer(player);
        CoreServer.get().getOnlinePlayers().add(player);
        player.setCurrentProxy(CoreServer.get().getProxies().getNode(proxy));
        player.setCurrentServer(CoreServer.get().getGameServers().getNode(server));
        Log.$(player.getName() + " joined proxy: " + proxy);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {
    }

}
