package org.kvlt.core.nodes;

import org.kvlt.core.packets.PacketOld;

/**
 * Служит для хранения группы узлов
 * @param <T> тип узла (Proxy, GameServer, например)
 */
public interface NodeContainer<T extends Node> {

    void send(PacketOld packet);
    void send(PacketOld... packets);
    void addNode(T t);
    void removeNode(T t);
    T getNode(String node);

}
