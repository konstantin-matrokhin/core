package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.CoreEvent;

public class PlayerAuthedEvent extends PlayerEvent {

    public PlayerAuthedEvent(ServerPlayer player) {
        super(player);
    }
}
