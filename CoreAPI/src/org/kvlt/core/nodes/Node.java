package org.kvlt.core.nodes;

import io.netty.channel.Channel;
import org.kvlt.core.packets.PacketOld;

/**
 * Интерфейс для хранения информации об узле и для обмена данными между ними. Например,
 * сервер Spigot/Bukkit и BungeeCord.
 */
public interface Node {

    void send(PacketOld packet);
    void send(PacketOld... packets);
    void setName(String name);
    String getName();
    void setChannel(Channel channel);
    Channel getChannel();

}
