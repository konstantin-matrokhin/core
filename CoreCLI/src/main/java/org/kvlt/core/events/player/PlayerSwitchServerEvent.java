package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.CoreEvent;
import org.kvlt.core.nodes.GameServer;

public class PlayerSwitchServerEvent extends PlayerEvent {

    private GameServer to;

    public PlayerSwitchServerEvent(ServerPlayer player, GameServer to) {
        setPlayer(player);
        this.to = to;
    }

    public GameServer getTo() {
        return to;
    }

}
