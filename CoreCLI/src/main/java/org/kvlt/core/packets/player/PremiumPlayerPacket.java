package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.packets.proxy.PremiumListPacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PremiumPlayerPacket extends PlayerPacket {

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
    }

    @Override
    public void execute(Channel channel) {
        if (ensurePlayer()) {
            Core.get().getPremiumPlayers().add(getPlayer());
            new PremiumListPacket(Core.get().getPremiumPlayers().names()).send();
        }
    }

    @Override
    public int getId() {
        return Packets.PREMIUM_PLAYER_PACKET;
    }

}
