package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;

public class PlayerCommandEvent extends PlayerEvent {

    private String command;

    public PlayerCommandEvent(ServerPlayer player, String command) {
        setPlayer(player);
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
