package org.kvlt.core.packets;

import org.kvlt.core.protocol.PacketIn;
import org.kvlt.core.protocol.PacketResolver;

public class CorePacketResolver implements PacketResolver {

    @Override
    public PacketIn getPacketIn(int id) {
        if (id == 1) {
            return new ProxyConnectPacket();
        }
        return null;
    }
}
