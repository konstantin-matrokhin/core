package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.Replies;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class PrivateMessagePacket implements PacketIn {

    public static final String RESPONSE = "Я -> %s: %s";
    public static final String RECEIVED = "%s -> вам: %s";

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
        ServerPlayer from = CoreServer.get().getOnlinePlayers().get(sender);
        ServerPlayer to = CoreServer.get().getOnlinePlayers().get(recipient);
        String response;
        String recipientsMessage;

        System.out.println(message);

        if (to != null) {
            response = String.format(RESPONSE, sender, message);
            recipientsMessage = String.format(RECEIVED, recipient, message);

            if (from != null) Replies.setCompanion(from, to);
            Replies.setCompanion(to, from);

            MessagePacket msg = new MessagePacket(recipient, recipientsMessage);
            to.getCurrentProxy().send(msg);
        } else {
            response = "Игрок не найден.";
        }

        MessagePacket responsePacket = new MessagePacket(sender, response);
        responsePacket.send(channel);
    }

    @Override
    public int getId() {
        return Packets.PM_PACKET;
    }

}
