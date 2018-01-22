package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.LangCommons;

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

        if (PlayerFactory.isStaff(player)) {
            ServerPlayer victimPlayer = Core.get().getOnlinePlayers().get(victim);
            if (victimPlayer != null) {
                victimPlayer.kick(reason);
                writeMsg("kicked", victim);
            } else {
                writeMsg("player-not-found");
            }
        } else {
            writeMsg(LangCommons.NO_PERM);
        }
        sendMsg(channel);
    }

    @Override
    public int getId() {
        return Packets.KICK_PACKET;
    }

}
