package org.kvlt.core.events;

import org.kvlt.core.entities.OnlinePlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerCoreJoinEvent implements CoreEvent {

    private OnlinePlayer player;

    public PlayerCoreJoinEvent(OnlinePlayer player) {
        this.player = player;
    }

    public OnlinePlayer getPlayer() {
        return player;
    }

}
