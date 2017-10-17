package org.kvlt.core.packets;

import io.netty.channel.Channel;
import org.kvlt.core.CoreServer;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.utils.Log;

public class ProxyConnectionPacket extends Packet<Channel> {

    private String name;

    public ProxyConnectionPacket(String name) {
        this.name = name;
    }

    @Override
    protected void onCore(Channel channel) {
        Proxy proxy = new Proxy(name, channel);
        Log.$("Connected Bungee > " + name);
        CoreServer.get().getProxies().add(proxy);
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
