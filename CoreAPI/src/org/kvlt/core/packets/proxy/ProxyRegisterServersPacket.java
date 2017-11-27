package org.kvlt.core.packets.proxy;

import org.kvlt.core.bungee.storages.ServersLoader;
import org.kvlt.core.packets.Packet;

public class ProxyRegisterServersPacket extends Packet {

    private String name;
    private String ip;
    private int port;

    public ProxyRegisterServersPacket(String name, String ip, int port) {
        this.name = name;
        this.ip = ip;
        this.port = port;
    }

    @Override
    protected void onCore() {

    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {
        ServersLoader.loadServer(name, ip, port);
    }
}
