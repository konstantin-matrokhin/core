package org.kvlt.core.protocol;

import org.kvlt.core.protocol.packets.ServerConnectPacket;
import org.kvlt.core.protocol.packets.NewTestPacket;
import org.kvlt.core.protocol.packets.ProxyConnectPacket;

import java.util.ArrayList;
import java.util.List;

public class Packets {

    private static List<Class<? extends Packet>> packets;

    static {
        packets = new ArrayList<>();
    }

    public static void registerCorePackets() {
        register(Packet.class); // dummy packet 'cause 1st packet isn't valid
        register(NewTestPacket.class);
        register(ProxyConnectPacket.class);
        register(ServerConnectPacket.class);
    }

    public static void register(Class<? extends Packet> packetClass) {
        packets.add(packetClass);
    }

    public static int getIdByClass(Class<? extends Packet> packetClass) {
        return packets.indexOf(packetClass);
    }

    public static Packet getById(byte id) {
        try {
            Class<? extends Packet> packetClass = packets.get(id);
            return packetClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
