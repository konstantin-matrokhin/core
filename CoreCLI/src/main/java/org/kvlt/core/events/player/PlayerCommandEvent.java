package org.kvlt.core.events.player;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.CoreEvent;

public class PlayerCommandEvent extends PlayerEvent {

    private String command;

    public PlayerCommandEvent(OnlinePlayer player, String command) {
        setPlayer(player);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
