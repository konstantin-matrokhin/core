package org.kvlt.core.packets.bukkit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

//TODO make IN ->
public class ServerListRequestPacket implements PacketIn {

    private String pattern;

    @Override
    public void read(ByteBuf in) {
        pattern = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        new ServerListPacket(pattern).send(channel);

//            GameServer[] serverArray = (GameServer[]) gameServers.list().toArray();
//            int length = serverArray.length;
//            String strArray[] = new String[length];
//            for (int i = 0; i < length; i++) {
//                strArray[i] = serverArray[i].getName();
//            }
    }

    @Override
    public int getId() {
        return Packets.SERVER_LIST_PACKET;
    }

}
