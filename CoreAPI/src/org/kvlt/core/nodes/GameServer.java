package org.kvlt.core.nodes;

import io.netty.channel.Channel;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;

import java.util.ArrayList;

public class GameServer implements Node {

    private Channel channel;
    private String name;
    private ArrayList<OnlinePlayer> onlinePlayers;

    @Override
    public void send(Packet packet) {
        channel.writeAndFlush(packet);
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

    public ArrayList<OnlinePlayer> getOnlinePlayers() {
        return onlinePlayers;
    }

}
