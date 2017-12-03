package org.kvlt.core.packets;

import java.util.HashMap;

public class Packets {

    private static HashMap<Byte, Packet> packets;

    static {
        packets = new HashMap<>();
    }

    public static void register(byte id, Packet packet) {
        packets.put(id, packet);
    }

    public static Packet getById(byte id) {
        try {
            Class<? extends Packet> packetClass = packets.get(id).getClass();
            return packetClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
