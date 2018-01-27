package org.kvlt.core.packets.bukkit;

import io.netty.buffer.ByteBuf;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.nodes.GameServers;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Finder;

import java.util.Set;
import java.util.stream.Collectors;

public class ServerListPacket extends CorePacketOut {

    private String pattern;

    public ServerListPacket(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void write(ByteBuf out) {
        GameServers gameServers = Finder.getGameServers(pattern);
        if (gameServers.list().size() > 0) {
            Set<String> nameSet = gameServers.list()
                    .stream()
                    .map(GameServer::getName)
                    .collect(Collectors.toSet());
            PacketUtil.writeStrings(nameSet, out);
        } else {
            PacketUtil.writeStringArray(new String[] {
                    "none"
            }, out);
        }
    }

    @Override
    public int getId() {
        return Packets.SERVER_LIST_PACKET;
    }

}
