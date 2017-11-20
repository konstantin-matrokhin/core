package org.kvlt.core.bukkit.utils;

import org.bukkit.entity.Player;
import org.kvlt.core.entities.OnlinePlayer;

public class BukkitPlayerAdapter {

    public static OnlinePlayer asOnlinePlayer(Player p) {
        OnlinePlayer op = new OnlinePlayer();
        op.setName(p.getName());
        op.setUuid(p.getUniqueId());
        op.setIp(p.getAddress().getHostString());

        return op;
    }
}
