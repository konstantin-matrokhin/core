package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerQuitEvent;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerQuitPacket implements PacketIn {

    private String playerName;

    @Override
    public void read(ByteBuf in) {
        playerName = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ServerPlayer op = Core.get().getOnlinePlayers().get(playerName);
        Core.get().getOnlinePlayers().remove(playerName);

        System.out.println(String.format("[-] %s вышел.", playerName));

        PlayerQuitEvent pqe = new PlayerQuitEvent(op);
        pqe.invoke();
    }

    @Override
    public int getId() {
        return Packets.PLAYER_QUIT_PACKET;
    }
}
