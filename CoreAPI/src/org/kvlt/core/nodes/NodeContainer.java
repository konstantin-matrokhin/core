package org.kvlt.core.nodes;

import org.kvlt.core.packets.Packet;

/**
 * Служит для хранения группы узлов
 * @param <T> тип узла (Proxy, GameServer, например)
 */
public interface NodeContainer<T extends Node> {

    void send(Packet packet);
    void send(Packet... packets);
    void addNode(T t);
    void removeNode(T t);
    T getNode(String node);

}
