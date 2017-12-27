package org.kvlt.core.bungee.auth;

import net.md_5.bungee.api.connection.ProxiedPlayer;

@SuppressWarnings("unused")
public class Premium {

    public static final String JOIN_URL =
            "https://sessionserver.mojang.com/session/minecraft/hasJoined?username=%s&serverId=%s&ip=%s";

    public static void check(ProxiedPlayer player) {
        String playerName = player.getName();

    }

}
