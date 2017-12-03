package org.kvlt.core.nodes;

import org.kvlt.core.packets.PacketOld;

import java.util.ArrayList;
import java.util.Iterator;

public class GameServers implements NodeContainer<GameServer>, Iterable<GameServer> {

    private ArrayList<GameServer> gameServers;

    {
        gameServers = new ArrayList<>();
    }

    @Override
    public void send(PacketOld packet) {
        gameServers.forEach(node -> node.send(packet));
    }

    @Override
    public void send(PacketOld... packets) {
        gameServers.forEach(node -> node.send(packets));
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
        return gameServers.stream()
                .filter(node -> node.getName().equalsIgnoreCase(nodeName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Iterator<GameServer> iterator() {
        return gameServers.iterator();
    }
}
