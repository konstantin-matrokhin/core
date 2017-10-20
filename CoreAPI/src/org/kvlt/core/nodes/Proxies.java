package org.kvlt.core.nodes;

import org.kvlt.core.packets.Packet;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Контейнер, хранящий в себе сущности прокси-серверов
 */
public class Proxies implements NodeContainer<Proxy> {

    private LinkedList<Proxy> proxies;

    {
        proxies = new LinkedList<>();
    }

    @Override
    public void send(Packet packet) {
        for (Proxy proxy: proxies) {
            proxy.send(packet);
        }
    }

    @Override
    public void addNode(Proxy proxy) {
        proxies.add(proxy);
    }

    @Override
    public void removeNode(Proxy proxy) {
        proxies.remove(proxy);
    }

    @Override
    public Proxy getNode(String nodeName) {
        for (Proxy proxy: proxies) {
            if (proxy.getName().equalsIgnoreCase(nodeName)) {
                return proxy;
            }
        }
        return null;
    }

}
