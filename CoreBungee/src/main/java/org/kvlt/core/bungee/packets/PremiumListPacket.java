package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

import java.util.Arrays;

public class PremiumListPacket implements PacketIn {

    private String[] players;

    @Override
    public void read(ByteBuf in) {
        players = PacketUtil.readStringArray(in);
    }

    @Override
    public void execute(Channel channel) {
        CoreBungee.get().getPremiumPlayers().clear();
        CoreBungee.get().getPremiumPlayers().addAll(Arrays.asList(players));
    }

    @Override
    public int getId() {
        return Packets.PREMIUM_LIST_PACKET;
    }

}
