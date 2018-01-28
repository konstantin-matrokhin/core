package org.kvlt.core;

import org.kvlt.core.commands.CommandListener;
import org.kvlt.core.entities.PlayerList;
import org.kvlt.core.entities.PremiumPlayers;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.nodes.*;
import org.kvlt.core.plugins.EventManager;
import org.kvlt.core.plugins.PluginLoader;
import org.kvlt.core.plugins.PluginManager;

public interface CoreAPI {

    ServerPlayer getOnlinePlayer(String name);

    ServerPlayer getUnloggedPlayer(String name);

    ServerPlayer getOfflinePlayer(String name);

    PlayerList<ServerPlayer> getOnlinePlayers();

    PlayerList<ServerPlayer> getUnloggedPlayers();

    PremiumPlayers getPremiumPlayers();

    Proxies getProxies();

    GameServers getGameServers();

    PluginLoader getPluginLoader();

    PluginManager getPluginManager();

    EventManager getEventManager();

    CommandListener getCommandListener();

    Server getServer();

    void init();

}
