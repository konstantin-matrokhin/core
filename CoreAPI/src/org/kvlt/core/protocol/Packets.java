package org.kvlt.core.protocol;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Packets {

    private static List<Class<? extends PacketIn>> packets;

    static {
        packets = new ArrayList<>();
    }

//    public static void registerCorePackets() {
//        register(Packet.class); // dummy packet 'cause 1st packet isn't valid
//        register(NewTestPacket.class);
//        register(ProxyConnectPacket.class);
//        register(ServerConnectPacket.class);
//    }

//    public static void register(Class<? extends Packet> packetClass) {
//        packets.add(packetClass);
//    }

    public static int getIdByClass(Class<? extends Packet> packetClass) {
        return packets.indexOf(packetClass);
    }

    public static PacketIn getById(byte id) {
        try {
            Class<? extends PacketIn> packetClass = packets.get(id);
            return packetClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
