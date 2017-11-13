package org.kvlt.core.packets.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.kvlt.core.packets.Packet;

public class ServerMessagePacket extends Packet {

    private static final String msgFormat = "&c(SERVER): %msg%";

    private String recepient;
    private String msg;

    public ServerMessagePacket(String recepient, String msg) {
        this.recepient = recepient;
        this.msg = msg;
    }

    @Override
    protected void onCore() {

    }

    @Override
    protected void onServer() {
        String formattedStr = ChatColor.translateAlternateColorCodes('&',
                msgFormat.replaceAll("%msg%", msg));
        Bukkit.getPlayer(recepient).sendMessage(formattedStr);
    }

    @Override
    protected void onProxy() {

    }
}
