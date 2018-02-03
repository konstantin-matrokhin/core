package org.kvlt.core.packets.bukkit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.netty.buffer.ByteBuf;
import org.kvlt.core.json.ServerInfo;
import org.kvlt.core.nodes.GameServers;
import org.kvlt.core.packets.CorePacketOut;
import org.kvlt.core.protocol.PacketUtil;
import org.kvlt.core.protocol.Packets;
import org.kvlt.core.utils.Finder;

public class ServerListPacket extends CorePacketOut {

    private static Gson gson;

    private String pattern;
    private int key;

    static {
        gson = new GsonBuilder()
                .serializeNulls()
                .create();
    }

    public ServerListPacket(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void write(ByteBuf out) {
        GameServers gameServers = Finder.getGameServers(pattern);
        String json;
        if (gameServers.list().size() > 0) {
            JsonArray jsonArray = new JsonArray();
            gameServers.forEach(server -> {
                ServerInfo serverInfo = new ServerInfo();
                serverInfo.setName(server.getName());
                serverInfo.setPort(server.getPort());
                JsonObject serverObject = gson.toJsonTree(serverInfo).getAsJsonObject();
                jsonArray.add(serverObject);
            });
            JsonObject preparedObject = new JsonObject();
            preparedObject.add("servers", jsonArray);
            json = preparedObject.toString();
        } else {
            System.out.println("empty");
            json = PacketUtil.EMPTY_STRING;
        }

        PacketUtil.writeString(json, out);
    }

    @Override
    public int getId() {
        return Packets.SERVER_LIST_PACKET;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

}
