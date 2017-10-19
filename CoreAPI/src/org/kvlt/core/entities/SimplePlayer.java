package org.kvlt.core.entities;

import java.util.UUID;

public class SimplePlayer extends ServerPlayer {

    private String name;
    private UUID uuid;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }


}
