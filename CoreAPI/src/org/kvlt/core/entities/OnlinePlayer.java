package org.kvlt.core.entities;

/**
 * Класс, который является сущностью игрока на сервере
 */
public class OnlinePlayer extends ServerPlayer {

    private String currentServer;

    public String getCurrentServer() {
        return currentServer;
    }

    public void setCurrentServer(String currentServer) {
        this.currentServer = currentServer;
    }
}
