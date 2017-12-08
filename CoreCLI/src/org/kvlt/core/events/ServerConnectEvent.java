package org.kvlt.core.events;

import org.kvlt.core.nodes.GameServer;

public class ServerConnectEvent implements CoreEvent {

    private GameServer server;

    public ServerConnectEvent() {}

    public ServerConnectEvent(GameServer server) {
        this.server = server;
    }

    public GameServer getServer() {
        return server;
    }

}
