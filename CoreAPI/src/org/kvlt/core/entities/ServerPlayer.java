package org.kvlt.core.entities;

import java.io.Serializable;
import java.util.UUID;

public abstract class ServerPlayer implements Serializable {

    private String name;
    private UUID uuid;

    public String getName() {
        return name;
    }

    public UUID getUUID() {
        return  uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

}
