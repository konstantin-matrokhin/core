package org.kvlt.core.nodes;

import org.kvlt.core.protocol.Packet;

import java.util.stream.Stream;

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
    Stream<T> stream();

}
