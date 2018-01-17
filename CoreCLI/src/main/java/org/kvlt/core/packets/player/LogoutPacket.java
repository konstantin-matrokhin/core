package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class LogoutPacket extends PlayerPacket {

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
    }

    @Override
    public void execute(Channel channel) {
        if (ensurePlayer()) {
            getPlayer().setLastIp(null);
        }
    }

    @Override
    public int getId() {
        return Packets.LOGOUT_PACKET;
    }

}
