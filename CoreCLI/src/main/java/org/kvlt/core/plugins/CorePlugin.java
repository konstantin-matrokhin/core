package org.kvlt.core.plugins;

import com.google.gson.JsonObject;
import org.kvlt.core.utils.Log;

public abstract class CorePlugin {

    private PluginConfig config;
    private PluginData pluginData;

    @SuppressWarnings("unused")
    private void init() {
        config = new PluginConfig();

        boolean created = config.createFile(pluginData.getName(), "config.json");
        if (!created) {
            Log.err("При создании конфига произошла ошибка!");
            return;
        }

        boolean loaded = config.loadConfig();
        if (loaded) {
            Log.$("Конфиг плагина " + this.pluginData.getName() + " загружен");
        } else {
            Log.err("Конфиг плагина " + this.pluginData.getName()+ " не загружен");
        }
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public JsonObject getConfig() {
       return config.getJsonObject();
    }

    public PluginData getPluginData() {
        return pluginData;
    }

    public void setPluginData(PluginData pluginData) {
        this.pluginData = pluginData;
    }

}
