package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.entities.Replies;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.CoreLocale;
import org.kvlt.core.utils.LangCommons;

public class PrivateMessagePacket implements PacketIn {

    private String sender;
    private String recipient;
    private String message;

    @Override
    public void read(ByteBuf in) {
        sender = PacketUtil.readString(in);
        recipient = PacketUtil.readString(in);
        message = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ServerPlayer from = Core.get().getOnlinePlayers().get(sender);
        ServerPlayer to = Core.get().getOnlinePlayers().get(recipient);
        String response;
        String recipientsMessage;

        if (to != null) {
            String responseTemplate = CoreLocale.get(from, "pm-response");
            String receiveTemplate = CoreLocale.get(to, "pr-receive");

            response = String.format(responseTemplate, sender, message);
            recipientsMessage = String.format(receiveTemplate, recipient, message);

            Replies.setCompanion(from, to);
            Replies.setCompanion(to, from);

            MessagePacket msg = new MessagePacket(recipient, recipientsMessage);
            to.getCurrentProxy().send(msg);
        } else {
            response = CoreLocale.get(from, LangCommons.PLAYER_NOT_FOUND);
        }

        MessagePacket responsePacket = new MessagePacket(sender, response);
        responsePacket.send(channel);
    }

    @Override
    public int getId() {
        return Packets.PM_PACKET;
    }

}
