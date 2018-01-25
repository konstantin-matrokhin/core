package org.kvlt.core.bungee.events;

import net.md_5.bungee.api.plugin.Event;
import org.kvlt.core.protocol.PacketIn;

public class PacketEvent extends Event {

    private final PacketIn packet;
    private final int id;

    public PacketEvent(PacketIn packet, int id) {
        this.packet = packet;
        this.id = id;
    }

    public PacketIn getPacket() {
        return packet;
    }

    public int getId() {
        return id;
    }

}
