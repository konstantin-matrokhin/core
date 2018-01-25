package org.kvlt.core.bungee.packets.protocol;

import org.kvlt.core.bungee.Core;
import org.kvlt.core.protocol.PacketOut;

public abstract class BungeePacketOut implements PacketOut {

    public void send() {
        Core.getAPI().sendPacket(this);
    }

}
