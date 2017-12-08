package org.kvlt.core.events.player;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.CoreEvent;
import org.kvlt.core.nodes.Proxy;

public class PlayerJoinEvent implements CoreEvent {

    private OnlinePlayer player;
    private Proxy proxy;

    public PlayerJoinEvent(OnlinePlayer player) {
        this.player = player;
        this.proxy = player.getCurrentProxy();
    }

    public OnlinePlayer getPlayer() {
        return player;
    }

    public Proxy getProxy() {
        return proxy;
    }
}
