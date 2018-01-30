package org.kvlt.core.packets.proxy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.CoreLocale;
import org.kvlt.core.utils.LangCommons;

public class BroadcastInPacket implements PacketIn {

    private String sender;
    private String message;
    private String server;

    @Override
    public void read(ByteBuf in) {
        sender = PacketUtil.readString(in);
        message = PacketUtil.readString(in);
        server = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        boolean everywhere = server.equalsIgnoreCase("none");

        if (Core.get().getOnlinePlayers().contains(sender)) {
            ServerPlayer player = Core.get().getOnlinePlayers().get(sender);
            if (!PlayerFactory.isStaff(player)) {
                new MessagePacket(sender,
                        CoreLocale.get(player.getLang(),
                                LangCommons.NO_PERM))
                        .send(channel);
                return;
            }
        }

        if (everywhere) {
            server = "@all";
        } else {
            server = "@" + server;
        }

        new BroadcastPacket(message).send(Destination.BUKKIT, server);
    }

    @Override
    public int getId() {
        return Packets.BROADCAST_PACKET;
    }
}
