package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;

public class    PlayerQuitEvent extends PlayerJoinEvent {

    public PlayerQuitEvent(ServerPlayer player) {
        super(player);
    }

}
