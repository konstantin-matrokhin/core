package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.Cancalleble;
import org.kvlt.core.nodes.Proxy;

public class PlayerLoginEvent extends PlayerEvent implements Cancalleble {

    public PlayerLoginEvent(ServerPlayer player) {
        setPlayer(player);
    }

    @Override
    public void cancel() {
        getPlayer().kick(null);
    }

}
