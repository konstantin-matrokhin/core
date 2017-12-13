package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.PROXY_MOTD_PACKET;

public class MotdPacket implements PacketIn {

    private String motd;

    @Override
    public void read(ByteBuf in) {
        motd = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        CoreBungee.get().getControlManager().getPingEventListener().setMotd(motd);
    }

    @Override
    public int getId() {
        return PROXY_MOTD_PACKET;
    }

}
