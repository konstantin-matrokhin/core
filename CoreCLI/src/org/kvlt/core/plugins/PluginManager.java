package org.kvlt.core.plugins;

import org.kvlt.core.events.CoreEvent;
import org.kvlt.core.events.CoreListener;
import org.kvlt.core.utils.Log;

import java.util.HashSet;
import java.util.Set;

public class PluginManager {

    private Set<CorePlugin> plugins;
    private EventManager eventManager;

    public PluginManager() {
        plugins = new HashSet<>();
        eventManager = new EventManager();
    }

    public void registerListener(CoreListener listener) {
        eventManager.getPlayerCoreJoinEvent().addListener(listener);
    }

    public boolean addPlugin(CorePlugin p) {
        return !hasPlugin(p.getPluginData().getName()) && plugins.add(p);
    }

    public void unloadPlugin() {

    }

    public void loadPlugin(CorePlugin plugin) {
        Log.$("Загружен плагин " + plugin.getPluginData().getName());
        plugin.onEnable();
    }

    public void reloadPlugin(String pluginName) {

    }

    public boolean hasPlugin(String pluginName) {
        return plugins.stream()
                .anyMatch(p -> p.getPluginData().getName().equalsIgnoreCase(pluginName));
    }

    public Set<CorePlugin> getPlugins() {
        return plugins;
    }

    public EventManager getEventManager() {
        return eventManager;
    }
}
