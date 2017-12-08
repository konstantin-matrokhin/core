package org.kvlt.core.events.bukkit;

import org.kvlt.core.nodes.GameServer;

public class ServerDisconnectEvent extends ServerConnectEvent {

    public ServerDisconnectEvent() {
        super();
    }

    public ServerDisconnectEvent(GameServer server) {
        super(server);
    }
}
