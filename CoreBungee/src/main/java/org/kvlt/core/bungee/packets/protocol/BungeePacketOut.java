package org.kvlt.core.bungee.packets.protocol;

import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.protocol.PacketOut;

public abstract class BungeePacketOut implements PacketOut {

    public void send() {
        CoreBungee.getAPI().sendPacket(this);
    }

}
