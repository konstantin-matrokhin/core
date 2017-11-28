package org.kvlt.core.plugins;

import org.kvlt.core.events.PlayerCoreJoinEvent;

public class EventManager {

    private PlayerCoreJoinEvent playerCoreJoinEvent;

    public EventManager() {
        playerCoreJoinEvent = new PlayerCoreJoinEvent();
    }

    public PlayerCoreJoinEvent getPlayerCoreJoinEvent() {
        return playerCoreJoinEvent;
    }
}
