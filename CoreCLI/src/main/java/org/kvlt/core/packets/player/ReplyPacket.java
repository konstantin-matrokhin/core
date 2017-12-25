package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.Replies;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.REPLY_PACKET;

public class ReplyPacket implements PacketIn {

    public static final String RESPONSE = "Я -> %s: %s";
    public static final String RECEIVED = "%s -> вам: %s";

    private String from;
    private String to;
    private String message;

    @Override
    public void read(ByteBuf in) {
        from = PacketUtil.readString(in);
        message = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ServerPlayer fromPlayer = CoreServer.get().getOnlinePlayers().get(from);

        ServerPlayer toPlayer;
        String response;

        if (fromPlayer == null) return;

        if (Replies.hasCompanion(fromPlayer)) {
            toPlayer = Replies.getCompanion(fromPlayer);
            if (CoreServer.get().getOnlinePlayers().contains(toPlayer)) {
                MessagePacket msg = new MessagePacket(to, String.format(RECEIVED, from, message));
                toPlayer.getCurrentProxy().send(msg);
                response = String.format(RESPONSE, to, message);
            } else {
                response = "Игрок, кажется, вышел :(";
            }
        } else {
            response = "Некому отвечать :(";
        }

        MessagePacket responsePacket = new MessagePacket(to, response);
        responsePacket.send(channel);

    }

    @Override
    public int getId() {
        return REPLY_PACKET;
    }

}
