package org.kvlt.core.bungee;

import io.netty.channel.Channel;
import net.md_5.bungee.config.Configuration;
import org.kvlt.core.bungee.net.ConnectionManager;
import org.kvlt.core.protocol.PacketOut;
import org.kvlt.core.protocol.PacketResolver;

import java.util.Set;

public interface ProxyCore {

    void connect();

    void disconnect();

    void sendPacket(PacketOut packet);

    Channel getServerChannel();

    PacketResolver getPacketResolver();

    Set<String> getPremiumPlayers();

    String getProxyName();

    void setProxyName(String name);

    Configuration getConfig();

    ConnectionManager getConnectionManager();

    ControlManager getControlManager();

}
