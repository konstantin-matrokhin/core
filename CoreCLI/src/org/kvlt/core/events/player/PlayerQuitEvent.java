package org.kvlt.core.events.player;

import org.kvlt.core.entities.OnlinePlayer;

public class PlayerQuitEvent extends PlayerJoinEvent {

    public PlayerQuitEvent() {
        super();
    }

    public PlayerQuitEvent(OnlinePlayer player) {
        super(player);
    }
}
