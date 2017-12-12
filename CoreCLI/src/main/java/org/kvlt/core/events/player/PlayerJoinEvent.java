package org.kvlt.core.events.player;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.CoreEvent;
import org.kvlt.core.nodes.Proxy;

public class PlayerJoinEvent extends PlayerEvent {

    private Proxy proxy;

    public PlayerJoinEvent(OnlinePlayer player) {
        setPlayer(player);
        this.proxy = player.getCurrentProxy();
    }

    public Proxy getProxy() {
        return proxy;
    }
}
