package org.kvlt.core.json;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class ServerListInfo extends JsonInfo {

    private static Gson gson;

    private List<ServerInfo> serverList = new ArrayList<>();

    static {
        gson = new GsonBuilder().create();
    }

    public ServerListInfo(String json) {
        super(json);

        JsonArray serverArray = getJson().getAsJsonArray("servers");
        serverArray.forEach(server -> {
            JsonObject serverObject = server.getAsJsonObject();
            System.out.println(serverObject);
            ServerInfo serverInfo = gson.fromJson(serverObject, ServerInfo.class);
            serverList.add(serverInfo);
        });
    }

    public List<ServerInfo> list() {
        return serverList;
    }

}
