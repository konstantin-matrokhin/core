package org.kvlt.core.protocol.packets.bukkit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.events.bukkit.ServerConnectEvent;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;

public class ServerConnectPacket implements PacketIn {

    private String name;
    private short port;

    @Override
    public void read(ByteBuf in) {
        name = PacketUtil.readString(in);
        port = in.readShort();
    }

    @Override
    public void execute(Channel channel) {
        System.out.println(String.format("Сервер присоединен (%s)", name));
        GameServer gs = new GameServer(name, port, channel);
        ServerConnectEvent sce = new ServerConnectEvent(gs);
        sce.invoke();
    }

    @Override
    public int getId() {
        return Packets.SERVER_CONNECT_PACKET;
    }

}
