package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class KickRequestPacket extends PlayerPacket {

    private String victim;
    private String reason;

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
        victim = PacketUtil.readString(in);
        reason = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        if (!ensurePlayer()) return;

        ServerPlayer player = getPlayer();
        String name = player.getName();
        String response;

        if (PlayerFactory.isStaff(player)) {
            ServerPlayer victimPlayer = Core.get().getOnlinePlayers().get(victim);
            if (victimPlayer != null) {
                victimPlayer.kick(reason);
                response = String.format("%s кикнут.", victim);
            } else {
                response = "Игрок не найден.";
            }
        } else {
            response = "You don't have permission!";
        }

        new MessagePacket(name, response);
    }

    @Override
    public int getId() {
        return Packets.KICK_PACKET;
    }

}
