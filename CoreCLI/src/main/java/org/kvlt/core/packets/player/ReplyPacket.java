package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.entities.Replies;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.utils.CoreLocale;

import static org.kvlt.core.protocol.Packets.REPLY_PACKET;

public class ReplyPacket implements PacketIn {

    public static final String RESPONSE = "Я -> %s: %s";
    public static final String RECEIVED = "%s -> вам: %s";

    private String from;
    private String message;

    @Override
    public void read(ByteBuf in) {
        from = PacketUtil.readString(in);
        message = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ServerPlayer fromPlayer = Core.get().getOnlinePlayers().get(from);

        ServerPlayer toPlayer;
        String response;

        if (fromPlayer == null) return;

        if (Replies.hasCompanion(fromPlayer)) {
            toPlayer = Replies.getCompanion(fromPlayer);
            String to = toPlayer.getName();
            if (Core.get().getOnlinePlayers().contains(toPlayer)) {
                String formatted = CoreLocale.get(toPlayer, "pm-receive", from, message);
                MessagePacket msg = new MessagePacket(to, formatted);
                toPlayer.getCurrentProxy().send(msg);
                response = CoreLocale.get(fromPlayer, "pm-response", to, message);
            } else {
                response = CoreLocale.get(fromPlayer, "reply-no-player");
            }
        } else {
            response = CoreLocale.get(fromPlayer, "reply-nobody");
        }

        MessagePacket responsePacket = new MessagePacket(from, response);
        responsePacket.send(channel);

    }

    @Override
    public int getId() {
        return REPLY_PACKET;
    }

}
