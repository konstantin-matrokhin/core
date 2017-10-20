package org.kvlt.core.packets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class BroadcastPacket extends Packet {

    private String str;

    public BroadcastPacket(String str) {
        this.str = str;
    }

    @Override
    protected void onCore() {

    }

    @Override
    protected void onServer() {
        Bukkit.broadcastMessage(
                ChatColor.translateAlternateColorCodes('&', str));
    }

    @Override
    protected void onProxy() {

    }
}
