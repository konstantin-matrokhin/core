package org.kvlt.core.entities;

import java.io.Serializable;
import java.util.UUID;

public class CorePlayer implements ServerPlayer, Serializable {

    private String name;
    private UUID uuid;

    public CorePlayer() {}
    public CorePlayer(String name) {
        this.name = name;
    }

    public CorePlayer(ServerPlayer player) {
        this.name = player.getName();
        this.uuid = player.getUUID();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
