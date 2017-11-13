package org.kvlt.core.packets.proxy;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.entities.SimplePlayer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

import java.io.Serializable;

public class ProxyLoginPacket extends Packet implements Serializable {

    private ServerPlayer player;
    private String server;

    public ProxyLoginPacket(ServerPlayer serverPlayer, String server) {
        this.player = serverPlayer;
        this.server = server;
    }

    @Override
    protected void onCore() {
        CoreServer.get().getServerPlayers().add(player);
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

    public String getServer() {
        return server;
    }

}
