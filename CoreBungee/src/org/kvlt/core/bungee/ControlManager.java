package org.kvlt.core.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import org.kvlt.core.bungee.entities.commands.ConnectCommand;
import org.kvlt.core.bungee.entities.commands.PingCommand;

public class ControlManager {

    private Plugin plugin;
    private PingEventListener pingEventListener;

    public ControlManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerCoreEvents() {
        pingEventListener = new PingEventListener();
        plugin.getProxy().getPluginManager().registerListener(plugin, new ProxyEventListener());
        plugin.getProxy().getPluginManager().registerListener(plugin, pingEventListener);
    }

    public void registerCoreCommands() {
        plugin.getProxy().getPluginManager().registerCommand(plugin, new PingCommand());
        plugin.getProxy().getPluginManager().registerCommand(plugin, new ConnectCommand());
    }

    public PingEventListener getPingEventListener() {
        return pingEventListener;
    }
}
