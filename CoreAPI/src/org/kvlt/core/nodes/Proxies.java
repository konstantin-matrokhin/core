package org.kvlt.core.nodes;

import org.kvlt.core.packets.Packet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Контейнер, хранящий в себе сущности прокси-серверов
 */
public class Proxies implements NodeContainer<Proxy>, Iterable<Proxy> {

    private List<Proxy> proxies;

    {
        proxies = new ArrayList<>();
    }

    @Override
    public void send(Packet packet) {
        proxies.forEach(proxy -> proxy.send(packet));
    }

    @Override
    public void send(Packet... packets) {
        proxies.forEach(proxy -> proxy.send(packets));
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
        return proxies
                .stream()
                .filter(node -> node.getName().equalsIgnoreCase(nodeName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Iterator<Proxy> iterator() {
        return proxies.iterator();
    }
}
