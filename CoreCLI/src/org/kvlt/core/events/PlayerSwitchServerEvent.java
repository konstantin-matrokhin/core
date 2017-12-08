package org.kvlt.core.events;

import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.nodes.GameServer;

public class PlayerSwitchServerEvent implements CoreEvent {

    private OnlinePlayer player;
    private GameServer to;

    public PlayerSwitchServerEvent() {}

    public PlayerSwitchServerEvent(OnlinePlayer player, GameServer to) {
        this.player = player;
        this.to = to;
    }

    public OnlinePlayer getPlayer() {
        return player;
    }

    public GameServer getTo() {
        return to;
    }
}
