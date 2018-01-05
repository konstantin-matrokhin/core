package org.kvlt.core.bungee.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class TransferPacket implements PacketIn {

    private String player;
    private String destination;

    @Override
    public void read(ByteBuf in) {
        player = PacketUtil.readString(in);
        destination = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        ProxiedPlayer pp = ProxyServer.getInstance().getPlayer(player);
        if (pp != null) {
            try {
                ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(destination);
                ProxyServer.getInstance().getPlayer(player).connect(serverInfo);
            } catch (Exception e) {
                pp.sendMessage(new TextComponent("Не удалось подключить к серверу!\n" +
                        "Возможно, его вообще нет на этом прокси.."));
            }
        }
    }

    @Override
    public int getId() {
        return Packets.PLAYER_TRANSFER_PACKET;
    }

}
