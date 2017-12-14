package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.PLAYER_AUTH_PACKET;

public class AuthPacket implements PacketIn {

    private String name;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ProxyLoggedPlayers.logIn(name);
    }

    @Override
    public int getId() {
        return PLAYER_AUTH_PACKET;
    }
}
