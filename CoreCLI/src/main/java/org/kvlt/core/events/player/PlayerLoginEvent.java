package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.Cancalleble;
import org.kvlt.core.nodes.Proxy;

public class PlayerLoginEvent extends PlayerEvent implements Cancalleble {

    private Proxy proxy;

    public PlayerLoginEvent(ServerPlayer player, Proxy proxy) {
        setPlayer(player);
        this.proxy = proxy;
    }

    public Proxy getProxy() {
        return proxy;
    }

    @Override
    public void cancel() {
        getPlayer().kick(null);
    }

}
