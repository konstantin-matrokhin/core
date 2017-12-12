package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.PLAYER_KICK_PACKET;

public class KickPacket implements PacketIn {

    private String name;
    private String reason;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
        reason = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(name);

        if (player != null) {
            player.disconnect(TextComponent.fromLegacyText(reason));
        }
    }

    @Override
    public int getId() {
        return PLAYER_KICK_PACKET;
    }

}
