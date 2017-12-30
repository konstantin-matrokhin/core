package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class BanPacket implements PacketIn {

    private String enforcer;
    private String victim;
    private String time;
    private String reason;

    @Override
    public void read(ByteBuf in) {
        enforcer = PacketUtil.readString(in);
        victim = PacketUtil.readString(in);
        time = PacketUtil.readString(in);
        reason = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {

    }

    @Override
    public int getId() {
        return Packets.BAN_PACKET;
    }

}
