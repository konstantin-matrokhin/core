package org.kvlt.core.bukkit.events;

import org.bukkit.entity.Player;
import org.kvlt.core.entities.OnlinePlayer;

public class BukkitPlayerAdapter {

    public static OnlinePlayer asOnlinePlayer(Player p) {
        OnlinePlayer op = new OnlinePlayer();
        op.setName(p.getName());
        op.setUUID(p.getUniqueId());
        return op;
    }
}
