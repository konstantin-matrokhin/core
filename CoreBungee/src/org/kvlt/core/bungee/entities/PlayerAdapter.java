package org.kvlt.core.bungee.entities;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.entities.OnlinePlayer;
public class PlayerAdapter {

    public static OnlinePlayer asOnlinePlayer(ProxiedPlayer pp) {
        OnlinePlayer cp = new OnlinePlayer();
        cp.setUuid(pp.getUniqueId());
        cp.setName(pp.getName());

        return cp;
    }

}
