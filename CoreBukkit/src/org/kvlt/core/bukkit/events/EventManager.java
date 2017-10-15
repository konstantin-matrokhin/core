package org.kvlt.core.bukkit.events;

import org.bukkit.Bukkit;
import org.kvlt.core.bukkit.CorePlugin;

public class EventManager {

    private static EventManager instance;

    private EventManager() {}

    public void start() {
        Bukkit.getPluginManager().registerEvents(new PlayerBasicEventListener(), CorePlugin.get());
    }

    public static synchronized EventManager get() {
        return instance == null ? instance = new EventManager() : instance;
    }

}
