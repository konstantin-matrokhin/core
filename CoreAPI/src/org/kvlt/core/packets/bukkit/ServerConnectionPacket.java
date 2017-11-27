package org.kvlt.core.packets.bukkit;

import io.netty.channel.Channel;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.packets.proxy.ProxyRegisterServersPacket;
import org.kvlt.core.packets.type.Core;
import org.kvlt.core.utils.Log;

@Core
public class ServerConnectionPacket extends Packet<Channel> {

    private String serverName;
    private int port;

    public ServerConnectionPacket(String serverName, String ip, int port) {
        this.serverName = serverName;
        this.port = port;
    }

    @Override
    public void onCore(Channel channel) {
        GameServer gs = new GameServer(serverName, channel);
        String ip = channel.remoteAddress().toString();

        ProxyRegisterServersPacket prsp = new ProxyRegisterServersPacket(serverName, ip, port);
        Log.$("Подключен сервер " + gs.getName());
    }

    @Override
    protected void onCore() {

    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }
}
