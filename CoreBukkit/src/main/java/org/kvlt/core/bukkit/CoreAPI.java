package org.kvlt.core.bukkit;

import io.netty.channel.Channel;
import org.bukkit.entity.Player;
import org.kvlt.core.json.PlayerInfo;
import org.kvlt.core.json.ServerInfo;
import org.kvlt.core.protocol.PacketOut;
import org.kvlt.core.protocol.PacketResolver;

import java.util.List;

public interface CoreAPI {
    String getName();

    void connect();

    void disconnect();

    void sendPacket(PacketOut packet);

    void transfer(Player player, String serverPattern);

    Channel getPlayerInfo();

    void setServer(Channel channel);

    PacketResolver getPacketResolver();

    PlayerInfo getPlayerInfo(String playerName);

    ServerInfo getServerInfo(String serverName);

    List<ServerInfo> getServers(String pattern);

}
