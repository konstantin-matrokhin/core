package org.kvlt.core.nodes;

import io.netty.channel.Channel;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.PlayerList;
import org.kvlt.core.packets.Packet;

import java.io.Serializable;

/**
 * Сущность, представляющая собой прокси-сервер
 * Хранит имя и канал, по которому можно отправить пакет
 */
public class Proxy implements Node, Serializable {

    private String name;
    private Channel channel;
    private PlayerList<OnlinePlayer> players;

    public Proxy(String name, Channel channel) {
        players = new PlayerList<>();

        this.name = name;
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public void send(Packet packet) {
        channel.writeAndFlush(packet, channel.voidPromise());
    }

    @Override
    public void send(Packet... packets) {
        for (Packet packet: packets) {
            channel.write(packet, channel.voidPromise());
        }
        channel.flush();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public PlayerList<OnlinePlayer> getPlayers() {
        return players;
    }

}
