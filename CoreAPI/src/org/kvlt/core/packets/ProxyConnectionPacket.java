package org.kvlt.core.packets;

import org.kvlt.core.CoreServer;
import org.kvlt.core.nodes.Proxy;
import org.kvlt.core.utils.Log;

public class ProxyConnectionPacket extends Packet {

    private String name;

    public ProxyConnectionPacket(String name) {
        this.name = name;
    }

    @Override
    protected void onCore() {
        Log.$("Connected Bungee > " + name);
        CoreServer.get().getProxies().add(new Proxy(name));
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }
}
