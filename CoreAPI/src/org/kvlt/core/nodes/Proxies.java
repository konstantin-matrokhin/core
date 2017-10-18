package org.kvlt.core.nodes;

import org.kvlt.core.packets.Packet;

import java.util.ArrayList;

public class Proxies extends ArrayList<Proxy> implements NodeContainer {

    @Override
    public void send(Packet packet) {
        for (Proxy proxy: this) {
            proxy.send(packet);
        }
    }

}
