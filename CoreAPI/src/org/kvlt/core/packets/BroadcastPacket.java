package org.kvlt.core.packets;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.kvlt.core.CoreServer;
import org.kvlt.core.utils.Log;
import org.kvlt.core.utils.LogType;

public class BroadcastPacket extends Packet {

    private String str;

    public BroadcastPacket(String str) {
        this.str = str;
    }

    @Override
    protected void onCore() {
        Log.$(LogType.BROADCAST, str);
        CoreServer.get().getGameServers().send(this);
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
