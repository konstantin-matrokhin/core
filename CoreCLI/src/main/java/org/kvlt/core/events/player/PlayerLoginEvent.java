package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.Cancellable;

public class PlayerLoginEvent extends PlayerEvent implements Cancellable {

    //todo сделать причину отмены входа

    public PlayerLoginEvent(ServerPlayer player) {
        super(player);
    }

    @Override
    public void setCancelled(boolean cancelled) {

    }

    @Override
    public boolean isCancelled() {
        return false;
    }

}
