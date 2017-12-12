package org.kvlt.core.events.bukkit;

import org.kvlt.core.events.CoreEvent;
import org.kvlt.core.nodes.GameServer;

public class ServerConnectEvent implements CoreEvent {

    private GameServer server;

    public ServerConnectEvent(GameServer server) {
        this.server = server;
    }

    public GameServer getServer() {
        return server;
    }

}
