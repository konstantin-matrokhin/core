package org.kvlt.core.bungee.storages;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.net.InetSocketAddress;

@Deprecated
public class ServersLoader {

    public static void loadServer(String name, String ip, int port) {
        InetSocketAddress address = new InetSocketAddress(ip, port);

        ServerInfo newServer = ProxyServer.getInstance()
                .constructServerInfo(name, address, "", false);

        ProxyServer.getInstance().getServers().put(name, newServer);
    }

}
