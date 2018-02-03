package org.kvlt.core.nodes;

import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.entities.PlayerList;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.Packet;

import java.io.Serializable;

public class GameServer implements Node, Serializable {

    private transient Channel channel;
    private String name;
    private short port;
    private PlayerList<ServerPlayer> onlinePlayers;

    {
        onlinePlayers = new PlayerList<>();
    }

    public GameServer(String name, short port, Channel channel) {
        this.name = name;
        this.port = port;
        this.channel = channel;
        Core.get().getGameServers().addNode(this);
    }

    @Override
    public void send(Packet packet) {
        channel.writeAndFlush(packet, channel.voidPromise());
    }

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

    @Override
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

    public PlayerList<ServerPlayer> getOnlinePlayers() {
        return onlinePlayers;
    }

    public short getPort() {
        return port;
    }

    @Override
    public String toString() {
        return getName();
    }
}
