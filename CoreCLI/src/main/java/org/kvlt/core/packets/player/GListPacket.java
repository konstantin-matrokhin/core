package org.kvlt.core.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.MessagePacket;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Finder;

import java.util.List;

public class GListPacket extends PlayerPacket {

    private String pattern;

    @Override
    public void read(ByteBuf in) {
        setPlayerName(PacketUtil.readString(in));
        pattern = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        if (!ensurePlayer()) return;

        StringBuilder response = new StringBuilder("Игроков онлайн: ");
        if (pattern.equalsIgnoreCase("none")) {
            int online = Core.get().getOnlinePlayers().size();
            response.append("(всего) ");
            response.append(online);
        } else {
            List<GameServer> serverList = Finder.getGameServers(pattern).list();
            GameServer server;
            if (serverList != null && serverList.size() > 0) {
                server = serverList.get(0);
                response.append(String.format("(%s) ", server.getName()));
                response.append(server.getOnlinePlayers().size());
            } else {
                response.append("сервер не найден.");
            }
        }
        new MessagePacket(getPlayerName(), response.toString()).send(channel);
    }

    @Override
    public int getId() {
        return Packets.G_LIST_PACKET;
    }

}
