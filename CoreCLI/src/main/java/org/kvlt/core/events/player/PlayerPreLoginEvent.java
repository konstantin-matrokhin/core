package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.Cancelleble;

public class PlayerPreLoginEvent extends PlayerEvent implements Cancelleble {

    public PlayerPreLoginEvent(ServerPlayer player) {
        setPlayer(player);
    }

    @Override
    public void cancel() {
        getPlayer().kick(null);
    }

}
