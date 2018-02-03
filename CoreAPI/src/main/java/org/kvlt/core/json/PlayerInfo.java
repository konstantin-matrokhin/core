package org.kvlt.core.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class PlayerInfo extends JsonInfo {

    private int id;
    private String name;
    private String server;
    private String ip;
    private String displayName;
    private String language;
    private String password;
    private int group;

    public PlayerInfo(String json) {
        super(json);

        name = getString("name");
        ip = getString("ip");
        language = getString("lang");
        password = getString("password");

        id = getJson().get("id").getAsInt();
        group = getJson().get("group").getAsInt();
        JsonElement serverJson = getJson().get("currentServer");
        if (!serverJson.isJsonNull()) {
            server = serverJson
                    .getAsJsonObject()
                    .getAsJsonPrimitive("name")
                    .getAsString();
        } else {
            server = null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

}
