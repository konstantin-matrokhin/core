package org.kvlt.core.bukkit.net;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.json.PlayerInfo;
import org.kvlt.core.json.ServerInfo;
import org.kvlt.core.bukkit.packets.PlayerDataPacket;
import org.kvlt.core.bukkit.packets.PlayerDataRequestPacket;
import org.kvlt.core.bukkit.packets.ServerListRequestPacket;
import org.kvlt.core.json.JsonPacket;
import org.kvlt.core.json.ServerListInfo;
import org.kvlt.core.protocol.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JsonPacketWorker {

    private static final Object lock = new Object();

    private static PacketResolver resolver = CorePlugin.getAPI().getPacketResolver();
    private static JsonParser jsonParser = new JsonParser();

    public static JsonObject getJsonPacketResponse(PacketOut packet) {
        int id = packet.getId();
        int key = packet.getKey();
        packet.send();

        System.out.println(String.format("PACKET KEY IS %d", key));

        PacketIn in;
        synchronized (lock) {
            long start = System.currentTimeMillis();
            while ((in = resolver.getResponsePacket(key)) == null) {
                try {
                    if (start + TimeUnit.SECONDS.toMillis(5) > System.currentTimeMillis()) {
                        break;
                    }
                } catch (Exception ignored) { }
            }
        }

        System.out.println("DONE");

        if (in instanceof JsonPacket && in.getId() == id) {
            JsonPacket jsonPacket = (JsonPacket) in;
            String json = jsonPacket.getJson();
            if (!json.equals(PacketUtil.EMPTY_STRING)) {
                System.out.println("VSE");
                return jsonParser.parse(json).getAsJsonObject();
            }
        } else {
            System.out.println("WRONG");
        }
        return null;
    }

    @Deprecated
    public static JsonObject getPlayerDataPacket(String player) {
        PlayerDataRequestPacket packetOut = new PlayerDataRequestPacket(player);
        int id = packetOut.getId();
        int key = packetOut.getKey();

        packetOut.send();

        PacketIn in;
        synchronized (lock) {
            while ((in = resolver.getResponsePacket(key)) == null) {
                try {
                    lock.wait();
                } catch (Exception ignored) { }
            }
        }

        if (in instanceof PlayerDataPacket) {
            PlayerDataPacket playerDataPacket = (PlayerDataPacket) in;
            String jsonDataString = playerDataPacket.getJson();
            if (!jsonDataString.equals(PacketUtil.EMPTY_STRING)) {
                JsonObject jsonData = jsonParser.parse(jsonDataString).getAsJsonObject();
                return jsonData;
            }
        }
        return null;
    }

    public static PlayerInfo getPlayerInfo(String name) {
        String json = getJsonPacketResponse(new PlayerDataRequestPacket(name)).toString();
        return new PlayerInfo(json);
    }

    public static ServerInfo getServerInfo(String serverName) {
//        String json = getJsonPacketResponse(new ServerDataRequestPacket(serverName)).toString();
//        return new ServerInfo(json);
        return null;
    }

    public static List<ServerInfo> getServers(String pattern) {
        String json = getJsonPacketResponse(new ServerListRequestPacket(pattern)).toString();
        return new ServerListInfo(json).list();
    }

}
