package org.kvlt.core.protocol.packets;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.type.Core;
import org.kvlt.core.protocol.Packet;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.utils.Log;

@Core
public class ServerConnectPacket extends Packet<Channel> {

    private String serverName;
    private int port;

    public ServerConnectPacket() {}

    public ServerConnectPacket(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }

    @Override
    public void execute(Channel channel) {
        GameServer gs = new GameServer(serverName, channel);
        String ip = channel.remoteAddress().toString();

        //ProxyRegisterServersPacket prsp = new ProxyRegisterServersPacket(serverName, ip, port);
        Log.$("Подключен сервер " + gs.getName());
    }

    @Override
    public void send() {

    }

    @Override
    public void readBytes(ByteBuf byteBuf) {
        serverName = PacketUtil.readString(byteBuf);
        port = byteBuf.readInt();
    }

    @Override
    public void writeBytes(ByteBuf byteBuf) {
        PacketUtil.writeString(serverName, byteBuf);
        byteBuf.writeInt(port);
    }

}
