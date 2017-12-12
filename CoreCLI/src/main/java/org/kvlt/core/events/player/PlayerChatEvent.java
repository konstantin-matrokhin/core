package org.kvlt.core.events.player;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.CoreEvent;

public class PlayerChatEvent implements CoreEvent {

    private OnlinePlayer player;
    private String message;

    public PlayerChatEvent() {}

    public PlayerChatEvent(OnlinePlayer player, String message) {
        this.player = player;
        this.message = message;
    }

    public OnlinePlayer getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }
}
