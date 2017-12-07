package org.kvlt.core.bungee.packets;

import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.protocol.PacketOut;

public abstract class BungeeOutPacket implements PacketOut {

    @Override
    public void send() {
        CoreBungee.get().sendPacket(this);
    }

}
