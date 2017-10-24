package org.kvlt.core.nodes;

import com.sun.istack.internal.NotNull;
import org.kvlt.core.packets.Packet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class GameServers implements NodeContainer<GameServer>, Iterable<GameServer> {

    private ArrayList<GameServer> gameServers;

    {
        gameServers = new ArrayList<>();
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
        return gameServers.stream().filter(node -> node.getName().equalsIgnoreCase(nodeName)).findFirst().get();
    }

    @Override
    public Iterator<GameServer> iterator() {
        return gameServers.iterator();
    }
}
