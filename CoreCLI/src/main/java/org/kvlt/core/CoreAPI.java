package org.kvlt.core;

import org.kvlt.core.nodes.GameServers;
import org.kvlt.core.nodes.Proxies;
import org.kvlt.core.plugins.EventManager;
import org.kvlt.core.plugins.PluginLoader;
import org.kvlt.core.plugins.PluginManager;

@Deprecated
@SuppressWarnings("unused")
public class CoreAPI {

    public static GameServers getGameServer() {
        return CoreServer.get().getGameServers();
    }

    public static Proxies getProxies() {
        return CoreServer.get().getProxies();
    }

    public static PluginLoader getPluginLoader() {
        return CoreServer.get().getPluginLoader();
    }

    public static PluginManager getPluginManager() {
        return CoreServer.get().getPluginManager();
    }

    public static EventManager getEventManager() {
        return CoreServer.get().getEventManager();
    }

}
