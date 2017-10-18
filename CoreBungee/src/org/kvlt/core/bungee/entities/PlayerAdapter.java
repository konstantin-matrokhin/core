package org.kvlt.core.bungee.entities;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.entities.CorePlayer;

public class PlayerAdapter {

    public static CorePlayer asCorePlayer(ProxiedPlayer pp) {
        CorePlayer cp = new CorePlayer();
        cp.setUuid(pp.getUniqueId());
        cp.setName(pp.getName());
        return cp;
    }

}
