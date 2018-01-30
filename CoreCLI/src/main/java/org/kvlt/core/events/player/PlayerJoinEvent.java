package org.kvlt.core.events.player;

import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.Cancellable;
import org.kvlt.core.events.EventResult;
import org.kvlt.core.events.Resultable;
import org.kvlt.core.nodes.Proxy;

public class PlayerJoinEvent extends PlayerEvent implements Cancellable, Resultable {

    private Proxy proxy;
    private boolean cancelled;

    //todo заюзать прокси и эвент почему-то вызывается в 2х местах
    public PlayerJoinEvent(ServerPlayer player) {
        super(player);
    }

    public Proxy getProxy() {
        return proxy;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setResult(EventResult result, String response) {
        //todo кикать после инвока эвента и отсюда вообще его убрать
        if (result == EventResult.KICK) {
            getPlayer().kick(response);
        }
    }

}
