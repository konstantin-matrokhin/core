package org.kvlt.core.bungee.entities;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.entities.ServerPlayer;

import java.util.UUID;

public class ProxyPlayerAdapter implements ServerPlayer {

    public ProxyPlayerAdapter(ProxiedPlayer p) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public UUID getUUID() {
        return null;
    }
}
