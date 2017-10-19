package org.kvlt.core.entities;

import java.util.UUID;

public class OnlinePlayer extends ServerPlayer {

    private String currentServer;

    public String getCurrentServer() {
        return currentServer;
    }

    public void setCurrentServer(String currentServer) {
        this.currentServer = currentServer;
    }
}
