package org.kvlt.core.packets.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.kvlt.core.CoreServer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;
import org.kvlt.core.utils.LogType;

public class BroadcastPacket extends Packet {

    private String str;
    private String server;
    private String sender;

    public BroadcastPacket(String str) {
        this.str = str;
    }

    public BroadcastPacket(String str, String server, String sender) {
        this.str = str;
        this.server = server;
        this.sender = sender;
    }

    @Override
    protected void onCore() {
        Log.$(LogType.BROADCAST, "(" + sender + " @ " + server + ") " + str);
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
