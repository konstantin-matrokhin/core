package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerQuitEvent;
import org.kvlt.core.metrics.PlayedTimeCounter;
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
        if (op == null) return;

        PlayerQuitEvent pqe = new PlayerQuitEvent(op);
        pqe.invoke();

        op.setPlayedLastTime(PlayedTimeCounter.stop(op));
        op.setPlayedTotal(op.getPlayedLastTime() + op.getPlayedLastTime());
        op.setLastIp(op.getIp());

        PlayerFactory.updatePlayer(op);
        Core.get().getOnlinePlayers().remove(playerName);

        System.out.println(String.format("[-] %s вышел.", playerName));
    }

    @Override
    public int getId() {
        return Packets.PLAYER_QUIT_PACKET;
    }
}
