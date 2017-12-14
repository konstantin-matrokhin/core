package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.MESSAGE_PACKET;

public class MessagePacket implements PacketIn {

    private String recipient;
    private String message;

    @Override
    public void read(ByteBuf in) {
        recipient = PacketUtil.readString(in);
        message = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ProxiedPlayer p = ProxyServer.getInstance().getPlayer(recipient);
        if (p == null) return;

        p.sendMessage(new TextComponent(message));
    }

    @Override
    public int getId() {
        return MESSAGE_PACKET;
    }
}
