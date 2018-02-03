package org.kvlt.core.protocol;

import org.kvlt.core.json.JsonPacket;

import java.util.HashMap;
import java.util.Map;

/**
 * Служит для определения класса пакета по ID
 */
public class PacketResolver {

    private Map<Integer, Class<? extends PacketIn>> packetsMap;
    private Map<Integer, PacketIn> responsePackets;

    {
        packetsMap = new HashMap<>();
        responsePackets = new HashMap<>();
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

    public void addToResponses(PacketIn packet, int key) {
        responsePackets.put(key, packet);
    }

    public PacketIn getResponsePacket(int key) {
        return responsePackets.get(key);
    }

    public Map<Integer, PacketIn> responses() {
        return responsePackets;
    }

}
