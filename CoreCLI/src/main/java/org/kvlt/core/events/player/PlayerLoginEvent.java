package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.Cancellable;

public class PlayerLoginEvent extends PlayerEvent implements Cancellable {

    public PlayerLoginEvent(ServerPlayer player) {
        setPlayer(player);
    }

    @Override
    public void setCancelled(boolean cancelled) {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }
}
