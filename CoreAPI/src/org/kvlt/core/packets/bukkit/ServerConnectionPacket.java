package org.kvlt.core.packets.bukkit;

import io.netty.channel.Channel;
import org.kvlt.core.nodes.GameServer;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

public class ServerConnectionPacket extends Packet<Channel> {

    private String serverName;

    public ServerConnectionPacket(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public void onCore(Channel channel) {
        GameServer gs = new GameServer(serverName, channel);
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
