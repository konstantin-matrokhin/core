package net.enot.darta.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Енот on 23.04.2017.
 */
public class Player {

    private static Map<String, Player> players = new ConcurrentHashMap<>();

    public static Map<String, Player> getPlayers() {
        return players;
    }

    public static Player getPlayer(String name) {
        return players.get(name);
    }

    public static boolean containsPlayer(String name) {
        return players.containsKey(name);
    }

    private String name;
    private Proxy proxy;
    private Server server;

    public Player(String name, Proxy proxy, Server server) {
        this(name, proxy);
        setServer(server);
    }

    public Player(String name, Proxy proxy) {
        this.name = name;
        this.proxy = proxy;

        players.put(name, this);
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        if (this.server != null) {
            this.server.subtractOnline();
        }
        if (server != null){
            server.addOnline();
        }
        this.server = server;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public void remove() {
        if (this.server != null) {
            this.server.subtractOnline();
        }
        players.remove(name);
    }
}
