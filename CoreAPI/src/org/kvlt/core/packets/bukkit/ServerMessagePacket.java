package org.kvlt.core.packets.bukkit;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.kvlt.core.packets.PacketOld;
import org.kvlt.core.packets.type.Bungee;
import org.kvlt.core.packets.type.Spigot;

@Bungee
@Spigot
public class ServerMessagePacket extends PacketOld {

    private static final String msgFormat = "&c(CORE): &e%msg%";

    private String recipient;
    private String msg;

    public ServerMessagePacket(String recepient, String msg) {
        this.recipient = recepient;
        this.msg = msg;
    }

    @Override
    protected void onCore() {

    }

    @Override
    protected void onServer() {
        String replaced = msgFormat.replace("%msg%", msg);
        String formattedStr = ChatColor
                .translateAlternateColorCodes('&', replaced);

        Bukkit.getPlayer(recipient).sendMessage(formattedStr);
    }

    @Override
    protected void onProxy() {
        String replaced = msgFormat.replaceAll("%msg%", msg);
        String formattedStr = net.md_5.bungee.api.ChatColor
                .translateAlternateColorCodes('&', replaced);

        BungeeCord.getInstance()
                .getPlayer(recipient)
                .sendMessage(TextComponent.fromLegacyText(formattedStr));
    }
}
