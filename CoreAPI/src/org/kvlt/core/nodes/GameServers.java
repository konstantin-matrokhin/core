package org.kvlt.core.nodes;

import org.kvlt.core.packets.Packet;

import java.util.LinkedList;

public class GameServers implements NodeContainer<GameServer> {

    private LinkedList<GameServer> gameServers;

    {
        gameServers = new LinkedList<>();
    }

    @Override
    public void send(Packet packet) {
        for (Node node: gameServers) {
            node.send(packet);
        }
    }

    @Override
    public void addNode(GameServer node) {
        gameServers.add(node);
    }

    @Override
    public void removeNode(GameServer node) {
        gameServers.remove(node);
    }

    @Override
    public GameServer getNode(String nodeName) {
        for (GameServer node: gameServers) {
            if (node.getName().equalsIgnoreCase(nodeName)) {
                return node;
            }
        }
        return null;
    }
}
