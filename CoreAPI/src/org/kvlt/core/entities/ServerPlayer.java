package org.kvlt.core.entities;

import java.io.Serializable;
import java.util.UUID;

public abstract class ServerPlayer implements Serializable {

    public abstract String getName();
    public abstract UUID getUUID();
    public abstract void setName(String name);
    public abstract void setUUID(UUID uuid);

}
