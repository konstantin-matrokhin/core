package org.kvlt.core.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class JsonInfo {

    private transient JsonObject json;

    public JsonInfo(String json) {
        this.json = new JsonParser().parse(json).getAsJsonObject();
    }

    public JsonInfo() {

    }

    protected String getString(String property) {
        return getJson().get(property).getAsString();
    }

    protected int getNumber(String property) {
        return getJson().get(property).getAsInt();
    }

    protected JsonObject getJson() {
        return json;
    }
}
