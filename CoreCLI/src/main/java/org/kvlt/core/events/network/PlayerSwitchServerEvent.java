package org.kvlt.core.events.network;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.Cancellable;
import org.kvlt.core.events.player.PlayerEvent;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.MessagePacket;

public class PlayerSwitchServerEvent extends PlayerEvent implements Cancellable {
    
    private GameServer to;
    private boolean cancelled;

    public PlayerSwitchServerEvent(ServerPlayer player, GameServer to) {
        super(player);
        this.to = to;
    }

    public GameServer getTo() {
        return to;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

}
