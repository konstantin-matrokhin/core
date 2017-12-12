package org.kvlt.core.protocol.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.player.PlayerJoinEvent;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.protocol.PlayerPacket;

public class PlayerJoinPacket extends PlayerPacket {



    @Override
    public void read(ByteBuf in) {

    }

    @Override
    public void execute(Channel channel) {

    }

    @Override
    public int getId() {
        return Packets.PLAYER_JOIN_PACKET;
    }
}
