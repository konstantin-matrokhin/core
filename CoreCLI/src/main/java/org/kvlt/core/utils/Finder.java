package org.kvlt.core.utils;

import org.kvlt.core.Core;
import org.kvlt.core.nodes.GameServers;
import org.kvlt.core.nodes.Proxies;

public class Finder {

    public static Proxies getProxies(String pattern) {
        boolean patterned = pattern.startsWith("@");
        boolean toAll = pattern.equalsIgnoreCase("@all");

        if (toAll) return Core.get().getProxies();

        Proxies proxies = new Proxies();
        String searchStr = patterned
                ? pattern.substring(1, pattern.length())
                : pattern;

        Core.get().getProxies().forEach(proxy -> {
            if (patterned) {
                if (proxy.getName().startsWith(searchStr)) {
                    proxies.addNode(proxy);
                }
            } else {
                if (proxy.getName().equalsIgnoreCase(searchStr)) {
                    proxies.addNode(proxy);
                }
            }
        });

        return proxies;
    }

    public static GameServers getGameServers(String pattern) {
        boolean patterned = pattern.startsWith("@");
        boolean toAll = pattern.equalsIgnoreCase("@all");

        if (toAll) return Core.get().getGameServers();

        GameServers gameServers = new GameServers();
        String searchStr = patterned
                ? pattern.substring(1, pattern.length())
                : pattern;

        Core.get().getGameServers().forEach(gs -> {
            if (patterned) {
                if (gs.getName().startsWith(searchStr)) {
                    gameServers.addNode(gs);
                }
            } else {
                if (gs.getName().equalsIgnoreCase(searchStr)) {
                    gameServers.addNode(gs);
                }
            }
        });

        return gameServers;
    }

}
