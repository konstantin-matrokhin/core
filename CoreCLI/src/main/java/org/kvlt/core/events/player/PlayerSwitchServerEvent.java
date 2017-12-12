package org.kvlt.core.events.player;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.CoreEvent;
import org.kvlt.core.nodes.GameServer;

public class PlayerSwitchServerEvent extends PlayerEvent {

    private GameServer to;

    public PlayerSwitchServerEvent() {}

    public PlayerSwitchServerEvent(OnlinePlayer player, GameServer to) {
        setPlayer(player);
        this.to = to;
    }

    public GameServer getTo() {
        return to;
    }
}
