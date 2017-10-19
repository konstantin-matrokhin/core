package org.kvlt.core.bungee.entities;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.entities.SimplePlayer;

public class PlayerAdapter {

    public static ServerPlayer asServerPlayer(ProxiedPlayer pp) {
        ServerPlayer cp = new SimplePlayer();
        cp.setUUID(pp.getUniqueId());
        cp.setName(pp.getName());
        return cp;
    }

}
