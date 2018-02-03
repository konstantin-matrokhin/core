package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerDataRequestPacket implements PacketIn {

    private String playerName;
    private int key;

    @Override
    public void read(ByteBuf in) {
        playerName = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ServerPlayer player = PlayerFactory.getPlayer(playerName);
        PlayerDataPacket packet = new PlayerDataPacket(player);
        packet.setKey(getKey());
        packet.send(channel);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_DATA_PACKET;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

}
