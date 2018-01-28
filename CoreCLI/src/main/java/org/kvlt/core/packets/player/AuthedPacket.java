package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class AuthedPacket implements PacketIn {

    private String name;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        PlayerFactory.addTask(() -> {
            ServerPlayer player = Core.get().getUnloggedPlayer(name);
            if (player != null) {
                Core.get().getUnloggedPlayers().remove(player);
                Core.get().getOnlinePlayers().add(player);
                player.setOnline(true);
            }
        });
    }

    @Override
    public int getId() {
        return Packets.SUCCESS_AUTH_PACKET;
    }

}
