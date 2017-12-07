package org.kvlt.core.protocol;

import java.util.HashMap;

public class PacketResolver {

    private HashMap<Integer, Class<? extends PacketIn>> packetsMap;

    {
        packetsMap = new HashMap<>();
    }

    public void registerPacket(PacketIn packet) {
        packetsMap.put(packet.getId(), packet.getClass());
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
