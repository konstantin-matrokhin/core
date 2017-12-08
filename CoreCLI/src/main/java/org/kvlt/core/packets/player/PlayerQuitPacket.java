package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.events.player.PlayerQuitEvent;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

public class PlayerQuitPacket implements PacketIn {

    private String playerName;

    @Override
    public void read(ByteBuf in) {
        playerName = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        OnlinePlayer op = CoreServer.get().getOnlinePlayers().get(playerName);
        CoreServer.get().getOnlinePlayers().remove(playerName);

        PlayerQuitEvent pqe = new PlayerQuitEvent(op);
        pqe.invoke();
    }

    @Override
    public int getId() {
        return 7;
    }
}
