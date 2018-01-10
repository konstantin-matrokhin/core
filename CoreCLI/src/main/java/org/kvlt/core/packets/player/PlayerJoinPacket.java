package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerJoinEvent;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PlayerJoinPacket extends PlayerPacket {

    private String name;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ServerPlayer player = Core.get().getOnlinePlayers().get(name);

        if (player != null) {
            player.setJoinTime(System.currentTimeMillis());
            PlayerFactory.updatePlayer(player);

            PlayerJoinEvent pje = new PlayerJoinEvent(player);
            pje.invoke();
        }
    }

    @Override
    public int getId() {
        return Packets.PLAYER_JOIN_PACKET;
    }

}
