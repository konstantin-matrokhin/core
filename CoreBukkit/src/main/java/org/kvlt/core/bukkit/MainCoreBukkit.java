package org.kvlt.core.bukkit;

import io.netty.channel.Channel;
import org.bukkit.entity.Player;
import org.kvlt.core.bukkit.net.ConnectionManager;
import org.kvlt.core.bukkit.net.JsonPacketWorker;
import org.kvlt.core.bukkit.packets.TransferRequestPacket;
import org.kvlt.core.json.PlayerInfo;
import org.kvlt.core.json.ServerInfo;
import org.kvlt.core.protocol.PacketOut;
import org.kvlt.core.protocol.PacketResolver;

import java.util.List;
import java.util.Objects;

public class MainCoreBukkit implements CoreAPI {

    private final PacketResolver packetResolver;

    private Channel server;

    MainCoreBukkit() {
        packetResolver = new PacketResolver();
    }

    @Override
    public String getName() {
        return ConfigManager.getClientName();
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
    public Channel getPlayerInfo() {
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

    @Override
    public PlayerInfo getPlayerInfo(String playerName) {
        return JsonPacketWorker.getPlayerInfo(playerName);
    }

    @Override
    public ServerInfo getServerInfo(String serverName) {
        return JsonPacketWorker.getServerInfo(serverName);
    }

    @Override
    public List<ServerInfo> getServers(String pattern) {
        return JsonPacketWorker.getServers(pattern);
    }

}
