package org.kvlt.core.packets.bukkit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.events.bukkit.ServerDisconnectEvent;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class ServerDisconnectPacket implements PacketIn {

    private String name;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        System.out.println(String.format("Сервер отключен (%s)", name));
        GameServer gs = CoreServer.get().getGameServers().getNode(name);
        CoreServer.get().getGameServers().removeNode(gs);

        ServerDisconnectEvent sde = new ServerDisconnectEvent(gs);
        sde.invoke();
    }

    @Override
    public int getId() {
        return Packets.SERVER_DISCONNECT_PACKET;
    }
}
