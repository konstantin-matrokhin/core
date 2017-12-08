package org.kvlt.core.packets.bukkit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.events.bukkit.ServerDisconnectEvent;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;

public class ServerDisconnectPacket implements PacketIn {

    private String name;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        GameServer gs = CoreServer.get().getGameServers().getNode(name);
        CoreServer.get().getGameServers().removeNode(gs);

        ServerDisconnectEvent sde = new ServerDisconnectEvent(gs);
        sde.invoke();

    }

    @Override
    public int getId() {
        return 5;
    }
}
