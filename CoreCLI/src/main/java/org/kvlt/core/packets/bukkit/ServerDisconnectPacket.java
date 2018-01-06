package org.kvlt.core.packets.bukkit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.Core;
import org.kvlt.core.events.bukkit.ServerDisconnectEvent;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Log;

public class ServerDisconnectPacket implements PacketIn {

    private String name;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
    }

    @Override
    public void execute(Channel channel) {
        Log.$(String.format("[-] Сервер отключен (%s)", name));
        GameServer gs = Core.get().getGameServers().getNode(name);
        Core.get().getGameServers().removeNode(gs);

        ServerDisconnectEvent sde = new ServerDisconnectEvent(gs);
        sde.invoke();
    }

    @Override
    public int getId() {
        return Packets.SERVER_DISCONNECT_PACKET;
    }
}
