package org.kvlt.core.nodes;

import io.netty.channel.Channel;
import org.kvlt.core.protocol.Packet;

/**
 * Интерфейс для хранения информации об узле и для обмена данными между ними. Например,
 * сервер Spigot/Bukkit и BungeeCord.
 */
public interface Node {

    void send(Packet packet);
    void send(Packet... packets);
    void setName(String name);
    String getName();
    void setChannel(Channel channel);
    Channel getChannel();

}
