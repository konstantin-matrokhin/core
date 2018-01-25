package org.kvlt.core.bukkit;

import io.netty.channel.Channel;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.kvlt.core.bukkit.net.ConnectionManager;
import org.kvlt.core.bukkit.packets.TransferRequestPacket;
import org.kvlt.core.protocol.PacketOut;
import org.kvlt.core.protocol.PacketResolver;

import java.util.Objects;

public class MainCoreBukkit implements CoreAPI {

    private final JavaPlugin plugin;
    private final PacketResolver packetResolver;

    private Channel server;

    public MainCoreBukkit(JavaPlugin plugin) {
        this.plugin = plugin;

        packetResolver = new PacketResolver();
    }

    @Override
    public void connect() {
        ConnectionManager.get().startClient();
    }

    @Override
    public void disconnect() {
        ConnectionManager.get().disconnect();
    }

    @Override
    public void sendPacket(PacketOut packet) {
        ConnectionManager.get().sendPacket(packet);
    }

    @Override
    public void transfer(Player player, String serverPattern) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(serverPattern);

        String name = player.getName();
        new TransferRequestPacket(name, serverPattern).send();
    }

    @Override
    public void setLanguage(Player player, String lang) {

    }

    @Override
    public String getLanguage(Player player) {
        return null;
    }

    @Override
    public Channel getServer() {
        return server;
    }

    @Override
    public void setServer(Channel channel) {
        this.server = channel;
    }

    @Override
    public PacketResolver getPacketResolver() {
        return packetResolver;
    }
}
