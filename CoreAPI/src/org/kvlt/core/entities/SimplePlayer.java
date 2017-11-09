package org.kvlt.core.entities;

import java.util.UUID;

@Deprecated
public class SimplePlayer extends ServerPlayer {

    private String name;
    private UUID uuid;

    public SimplePlayer() {}

    public SimplePlayer(ServerPlayer player) {
        setName(player.getName());
        setUuid(player.getUuid());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


}
