package org.kvlt.core.protocol;

import java.util.HashMap;
import java.util.ServiceLoader;

/**
 * Служит для определения класса пакета по ID
 */
public class PacketResolver {

    private HashMap<Integer, Class<? extends PacketIn>> packetsMap;

    {
        packetsMap = new HashMap<>();
    }

    public void registerPacket(PacketIn packet) {
        packetsMap.put(packet.getId(), packet.getClass());
    }

    public void registerPackets(PacketIn[] packets) {
        for (PacketIn packet: packets) {
            registerPacket(packet);
        }
    }

    public PacketIn getPacketIn(int id) {
        Class<? extends PacketIn> packetClass = packetsMap.get(id);

        try {
            if (packetClass != null) {
                return packetClass.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
