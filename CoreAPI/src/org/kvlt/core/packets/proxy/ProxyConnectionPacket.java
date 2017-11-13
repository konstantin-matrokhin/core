package org.kvlt.core.packets.proxy;

import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.config.Config;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.utils.Log;

public class ProxyConnectionPacket extends Packet<Channel> {

    private String serverName;

    public ProxyConnectionPacket(String serverName) {
        this.serverName = serverName;
    }

    @Override
    protected void onCore(Channel channel) {
        Proxy proxy = new Proxy(serverName, channel);
        Log.$("Подключен прокси-сервер " + serverName);
        proxy.send(new ProxyPingDataPacket(Config.getProxy("motd")));
        CoreServer.get().getProxies().addNode(proxy);
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
