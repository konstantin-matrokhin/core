package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.LangCommons;
import org.kvlt.core.utils.CoreLocale;

public class PlayerInfoPacket implements PacketIn {

    private String sender;
    private String player;
    private boolean full;

    @Override
    public void read(ByteBuf in) {
        sender = PacketUtil.readString(in);
        player = PacketUtil.readString(in);
        full = in.readBoolean();
    }

    @Override
    public void execute(Channel channel) {
        Runnable r = () -> {
            String response;
            ServerPlayer senderPlayer = Core.get().getOnlinePlayers().get(sender);
            if (senderPlayer != null) {
                if (PlayerFactory.isStaff(senderPlayer)) {
                    response = full ? PlayerFactory.getPrettyInfo(player) : PlayerFactory.getShortInfo(player);
                } else {
                    response = CoreLocale.get(senderPlayer.getLang(), LangCommons.NO_PERM);
                }
                new MessagePacket(sender, response).send(channel);
            }
        };
        PlayerFactory.addTask(r);
    }

    @Override
    public int getId() {
        return Packets.PLAYER_INFO_PACKET;
    }

}
