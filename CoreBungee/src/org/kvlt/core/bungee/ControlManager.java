package org.kvlt.core.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import org.kvlt.core.bungee.commands.*;

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
        plugin.getProxy().getPluginManager().registerCommand(plugin, new LoginCommand());
        plugin.getProxy().getPluginManager().registerCommand(plugin, new RegisterCommand());
        plugin.getProxy().getPluginManager().registerCommand(plugin, new EmailCommand());
        plugin.getProxy().getPluginManager().registerCommand(plugin, new ReplyCommand());
    }

    public PingEventListener getPingEventListener() {
        return pingEventListener;
    }
}
