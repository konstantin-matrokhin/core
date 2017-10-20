package org.kvlt.core.nodes;

import org.kvlt.core.packets.Packet;

import java.util.ArrayList;

/**
 * Контейнер, хранящий в себе сущности прокси-серверов
 */
public class Proxies extends ArrayList<Proxy> implements NodeContainer {

    private LinkedList<>

    @Override
    public void send(Packet packet) {
        for (Proxy proxy: this) {
            proxy.send(packet);
        }
    }

    @Override
    public void addNode(Node node) {

    }

    @Override
    public void removeNode(Node node) {

    }

    @Override
    public Node getNode(String nodeName) {
        return null;
    }

}
