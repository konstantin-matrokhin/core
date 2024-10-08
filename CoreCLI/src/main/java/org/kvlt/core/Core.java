package org.kvlt.core;

import org.kvlt.core.commands.CommandListener;
import org.kvlt.core.db.HibernateInitializer;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.PlayerList;
import org.kvlt.core.entities.PremiumPlayers;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.nodes.GameServers;
import org.kvlt.core.nodes.Proxies;
import org.kvlt.core.plugins.EventManager;
import org.kvlt.core.plugins.PluginLoader;
import org.kvlt.core.plugins.PluginManager;

public final class Core implements CoreAPI {

    private static CoreAPI instance;

    private PlayerList<ServerPlayer> onlinePlayers;
    private PlayerList<ServerPlayer> unloggedPlayers;
    private PremiumPlayers premiumPlayers;

    private Proxies proxies;
    private GameServers gameServers;

    private PluginLoader pluginLoader;
    private PluginManager pluginManager;
    private EventManager eventManager;
    private CommandListener commandListener;
    private Server server;
    private boolean initialized;

    private Core() {}

    @Override
    public void init() {
        if (initialized) return;

        server = new CoreServer();
        onlinePlayers = new PlayerList<>();
        unloggedPlayers = new PlayerList<>();
        premiumPlayers = new PremiumPlayers(HibernateInitializer
                .getSessionFactory().openSession());
        premiumPlayers.loadFromMySQL();

        proxies = new Proxies();
        gameServers = new GameServers();
        eventManager = new EventManager();
        pluginManager = new PluginManager();
        pluginLoader = new PluginLoader(pluginManager);
        commandListener = new CommandListener();

        initialized = true;

        pluginLoader.loadPlugins();
        eventManager.initializeExternalEvents();

        server.start();
        commandListener.listen();
    }

    @Override
    public ServerPlayer getOnlinePlayer(String name) {
        return onlinePlayers.get(name);
    }

    @Override
    public ServerPlayer getUnloggedPlayer(String name) {
        return unloggedPlayers.get(name);
    }

    @Override
    public ServerPlayer getOfflinePlayer(String name) {
        return PlayerFactory.loadPlayer(name, false);
    }

    @Override
    public PlayerList<ServerPlayer> getOnlinePlayers() {
        return onlinePlayers;
    }

    @Override
    public PlayerList<ServerPlayer> getUnloggedPlayers() {
        return unloggedPlayers;
    }

    @Override
    public PremiumPlayers getPremiumPlayers() {
        return premiumPlayers;
    }

    @Override
    public Proxies getProxies() {
        return proxies;
    }

    @Override
    public GameServers getGameServers() {
        return gameServers;
    }

    @Override
    public PluginLoader getPluginLoader() {
        return pluginLoader;
    }

    @Override
    public PluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public CommandListener getCommandListener() {
        return commandListener;
    }

    @Override
    public Server getServer() {
        return server;
    }

    public static synchronized CoreAPI get() {
        return instance == null ? instance = new Core() : instance;
    }

}
