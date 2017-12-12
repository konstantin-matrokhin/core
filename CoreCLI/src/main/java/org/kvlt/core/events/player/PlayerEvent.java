package org.kvlt.core.events.player;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.CoreEvent;

public abstract class PlayerEvent implements CoreEvent {

    private OnlinePlayer player;

    public OnlinePlayer getPlayer() {
        return player;
    }

    protected void setPlayer(OnlinePlayer player) {
        this.player = player;
    }
}
