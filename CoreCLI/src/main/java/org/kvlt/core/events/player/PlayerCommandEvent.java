package org.kvlt.core.events.player;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.CoreEvent;

public class PlayerCommandEvent implements CoreEvent {

    private OnlinePlayer player;
    private String command;
    private String[] args;

    public PlayerCommandEvent() { }

    public PlayerCommandEvent(OnlinePlayer player, String command) {
        this.player = player;
        this.command = command;
    }

    public OnlinePlayer getPlayer() {
        return player;
    }

    public String getCommand() {
        return command;
    }
}
