package org.kvlt.core.packets;

import java.util.HashMap;

public class Packets {

    private static HashMap<Byte, Class<? extends Packet>> packets;

    static {
        packets = new HashMap<>();
    }

    public static void register(byte id, Class<? extends Packet> packetClass) {
        packets.put(id, packetClass);
    }

    public static void initAllPackets() {
        register((byte) 0x01, NewTestPacket.class);
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
