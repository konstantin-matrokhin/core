package org.kvlt.core.entities;

import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.nodes.Proxy;

/**
 * Класс, который является сущностью игрока на сервере
 */
public class OnlinePlayer extends ServerPlayer {

    private GameServer currentServer;
    private Proxy currentProxy;

    public OnlinePlayer() {}

    public OnlinePlayer(String name) {
        setName(name);
    }

    public GameServer getCurrentServer() {
        return currentServer;
    }

    public void setCurrentServer(GameServer currentServer) {
        this.currentServer = currentServer;
    }

    public Proxy getCurrentProxy() {
        return currentProxy;
    }

    public void setCurrentProxy(Proxy currentProxy) {
        this.currentProxy = currentProxy;
    }

}
