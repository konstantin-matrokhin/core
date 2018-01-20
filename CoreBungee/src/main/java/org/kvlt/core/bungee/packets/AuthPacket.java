package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.bungee.auth.Auth;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

import static org.kvlt.core.protocol.Packets.PLAYER_AUTH_PACKET;

public class AuthPacket implements PacketIn {

    private String name;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ProxyLoggedPlayers.logIn(name);
        try {
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(name);
            Auth.getAnnoyingMessages().get(player).cancel();
            Auth.getAnnoyingMessages().remove(player);
        } catch (Exception ignored) {}
    }

    @Override
    public int getId() {
        return PLAYER_AUTH_PACKET;
    }
}
