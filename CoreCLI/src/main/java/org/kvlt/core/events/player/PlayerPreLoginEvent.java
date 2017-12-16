package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.Cancalleble;
import org.kvlt.core.nodes.Proxy;

public class PlayerPreLoginEvent extends PlayerEvent implements Cancalleble {

    public PlayerPreLoginEvent(ServerPlayer player) {
        setPlayer(player);
    }

    @Override
    public void cancel() {
        getPlayer().kick(null);
    }

}
