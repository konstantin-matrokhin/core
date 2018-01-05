package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.nodes.Proxy;

public class PlayerJoinEvent extends PlayerEvent {

    private Proxy proxy;

    public PlayerJoinEvent(ServerPlayer player) {
    }

    public Proxy getProxy() {
        return proxy;
    }
}
