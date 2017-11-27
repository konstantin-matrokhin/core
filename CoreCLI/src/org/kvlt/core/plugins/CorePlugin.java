package org.kvlt.core.plugins;

public abstract class CorePlugin {

    private PluginData pluginData;

    public abstract void onEnable();

    public abstract void onDisable();


    public PluginData getPluginData() {
        return pluginData;
    }

    public void setPluginData(PluginData pluginData) {
        this.pluginData = pluginData;
    }

}
