package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.CoreEvent;

public abstract class PlayerEvent implements CoreEvent {

    private ServerPlayer player;

    protected PlayerEvent(ServerPlayer serverPlayer) {
        this.player = serverPlayer;
    }

    public ServerPlayer getPlayer() {
        return player;
    }
}
