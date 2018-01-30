package org.kvlt.core.bungee.storages;

import io.netty.util.internal.ConcurrentSet;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.bungee.auth.Auth;
import org.kvlt.core.bungee.packets.AuthedPacket;

import java.util.Set;

public class ProxyLoggedPlayers {

    private static Set<String> players;

    static {
        players = new ConcurrentSet<>();
    }

    public static void logIn(String player) {
        players.add(player);
        new AuthedPacket(player).send();

        try {
            ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(player);
            Auth.getAnnoyingMessages().get(pp).cancel();
            Auth.getAnnoyingMessages().remove(pp);
        } catch (Exception ignored) {}
    }

    public static void logOut(String player) {
        players.remove(player);
    }

    public static boolean isLogged(String player) {
        return players.contains(player);
    }

}
