package org.kvlt.core.bukkit;

import io.netty.channel.Channel;
import org.bukkit.entity.Player;
import org.kvlt.core.protocol.PacketOut;
import org.kvlt.core.protocol.PacketResolver;

public interface CoreAPI {
    String getName();

    void connect();

    void disconnect();

    void sendPacket(PacketOut packet);

    void transfer(Player player, String serverPattern);

    Channel getServer();

    void setServer(Channel channel);

    PacketResolver getPacketResolver();

}
