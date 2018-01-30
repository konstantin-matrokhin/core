package org.kvlt.core.bungee;

import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import org.kvlt.core.bungee.commands.*;
import org.kvlt.core.bungee.listener.AuthEventListener;
import org.kvlt.core.bungee.listener.PingEventListener;
import org.kvlt.core.bungee.listener.ProxyEventListener;

import java.util.ArrayList;
import java.util.List;

public class ControlManager {

    private Plugin plugin;
    private PingEventListener pingEventListener;

    public ControlManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void registerCoreEvents() {
        pingEventListener = new PingEventListener();

        plugin.getProxy().getPluginManager().registerListener(plugin, new AuthEventListener());
        plugin.getProxy().getPluginManager().registerListener(plugin, new ProxyEventListener());
        plugin.getProxy().getPluginManager().registerListener(plugin, pingEventListener);
    }

    public void registerCoreCommands() {
        List<Command> cmds = new ArrayList<Command>() {{
            add(new ReconnectCommand());
            add(new LoginCommand());
            add(new RegisterCommand());
            add(new EmailCommand());
            add(new PwdCommand());
            add(new KickCommand());
            add(new LogoutCommand());
            add(new LicenseCommand());
        }};

        cmds.forEach(c -> plugin.getProxy().getPluginManager().registerCommand(plugin, c));
    }

    public PingEventListener getPingEventListener() {
        return pingEventListener;
    }
}
