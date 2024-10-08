package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.bungee.auth.Auth;
import org.kvlt.core.bungee.storages.IdMap;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.ID_PACKET;

public class IdPacket implements PacketIn {

    private String playerName;
    private int id;

    @Override
    public void read(ByteBuf in) {
        playerName = PacketUtil.readString(in);
        id = in.readInt();
    }

    @Override
    public void execute(Channel channel) {
        IdMap.setId(playerName, id);
        System.out.println("Пришел айдишник " + id);
        try {
            Auth.trySessionAuth(playerName);
        } catch (Exception ignored) {}
    }

    @Override
    public int getId() {
        return ID_PACKET;
    }

}
