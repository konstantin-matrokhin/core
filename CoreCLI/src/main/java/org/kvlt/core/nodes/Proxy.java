package org.kvlt.core.nodes;

import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.entities.PlayerList;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.Packet;

/**
 * Сущность, представляющая собой прокси-сервер
 * Хранит имя и канал, по которому можно отправить пакет
 */
public class Proxy implements Node {

    private String name;
    private Channel channel;
    private PlayerList<ServerPlayer> players;

    public Proxy(String name, Channel channel) {
        players = new PlayerList<>();

        this.name = name;
        this.channel = channel;
        Core.get().getProxies().addNode(this);
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

    public PlayerList<ServerPlayer> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        return getName();
    }
}
