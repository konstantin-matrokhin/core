package org.kvlt.core.packets.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.kvlt.core.CoreServer;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.packets.type.Spigot;
import org.kvlt.core.utils.Log;
import org.kvlt.core.utils.LogType;

import java.util.Optional;

/**
 * Пакет для показа объявления для всех игроков
 * Может быть отправлено как на конкретный сервер, так и на все
 */
@Spigot
public class BroadcastPacket extends Packet {

    private final String PREFIX = "&7[&cОБЪЯВЛЕНИЕ&7]&r ";

    private String str;
    private Optional<String> server;
    private Optional<String> sender;

    public BroadcastPacket(String str) {
        this.str = str;
    }

    public BroadcastPacket(String str, String sender) {
        this.str = str;
        this.sender = Optional.ofNullable(sender);
    }

    public BroadcastPacket(String str, String server, String sender) {
        this.str = str;
        this.server = Optional.ofNullable(server);
        this.sender = Optional.ofNullable(sender);
    }

    @Override
    protected void onCore() {
        Log.$(LogType.BROADCAST, "(" + sender + ") " + str);

        if (server.isPresent()) {
            GameServer to = CoreServer.get().getGameServers().getNode(server.get());
            if (to != null) {
                to.send(this);
            }
        } else {
            CoreServer.get().getGameServers().send(this);
        }
    }

    @Override
    protected void onServer() {
        Bukkit.broadcastMessage(
                ChatColor.translateAlternateColorCodes('&', PREFIX + str));
    }

    @Override
    protected void onProxy() {

    }
}
