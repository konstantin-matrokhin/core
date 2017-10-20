package org.kvlt.core.nodes;

import org.kvlt.core.packets.Packet;

public interface NodeContainer<T extends Node> {

    void send(Packet packet);
    void addNode(T t);
    void removeNode(T t);
    T getNode(String node);

}
