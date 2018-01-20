package org.kvlt.core.nodes;

import org.kvlt.core.protocol.Packet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class GameServers implements NodeContainer<GameServer>, Iterable<GameServer> {

    private ArrayList<GameServer> gameServers;

    {
        gameServers = new ArrayList<>();
    }

    @Override
    public void send(Packet packet) {
        gameServers.forEach(node -> node.send(packet));
    }

    @Override
    public void send(Packet... packets) {
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

    @Override
    public Stream<GameServer> stream() {
        return gameServers.stream();
    }

    public List<GameServer> list() {
        return gameServers;
    }

}
