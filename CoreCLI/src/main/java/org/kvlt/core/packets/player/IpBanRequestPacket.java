package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class IpBanRequestPacket implements PacketIn {

    private String ip;

    @Override
    public void read(ByteBuf in) {
        ip = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        PlayerFactory.banIp(ip);
    }

    @Override
    public int getId() {
        return Packets.BAN_IP;
    }

}
