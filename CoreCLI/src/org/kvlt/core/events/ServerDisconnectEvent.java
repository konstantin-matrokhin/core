package org.kvlt.core.events;

import org.kvlt.core.nodes.GameServer;

public class ServerDisconnectEvent extends ServerConnectEvent {

    public ServerDisconnectEvent(GameServer server) {
        super(server);
    }
}
