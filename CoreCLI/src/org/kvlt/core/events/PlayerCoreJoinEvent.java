package org.kvlt.core.events;

import org.kvlt.core.entities.OnlinePlayer;

public class PlayerCoreJoinEvent implements CoreEvent {

    private OnlinePlayer player;

    public PlayerCoreJoinEvent(OnlinePlayer player) {
        this.player = player;
    }

    public OnlinePlayer getPlayer() {
        return player;
    }

}
