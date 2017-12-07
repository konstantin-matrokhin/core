package org.kvlt.core.bungee.storages;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.net.InetSocketAddress;

public class ServersLoader {

    public static void loadServer(String name, String ip, int port) {
        InetSocketAddress address = new InetSocketAddress(ip, port);

        ServerInfo newServer = BungeeCord.getInstance()
                .constructServerInfo(name, address, "", false);

        ProxyServer.getInstance().getServers().put(name, newServer);
    }

}
