package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.events.player.PlayerJoinEvent;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Log;

public class PlayerJoinPacket extends PlayerPacket {

    private String name;
    private ServerPlayer unloggedPlayer;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        unloggedPlayer = CoreServer.get().getUnloggedPlayers().get(name);
        if (unloggedPlayer == null) return;

        PlayerJoinEvent pje = new PlayerJoinEvent(unloggedPlayer);
        pje.invoke();
    }

    @Override
    public int getId() {
        return Packets.PLAYER_JOIN_PACKET;
    }

}
