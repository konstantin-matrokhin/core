package org.kvlt.core.events.player;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.CoreEvent;

public class PlayerChatEvent extends PlayerEvent {

    private String message;

    public PlayerChatEvent(OnlinePlayer player, String message) {
        setPlayer(player);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
