package org.kvlt.core.bungee;

import io.netty.channel.Channel;
import net.md_5.bungee.api.plugin.Plugin;
import org.kvlt.core.bungee.net.ConnectionManager;
import org.kvlt.core.protocol.PacketOut;

import java.io.File;
import java.io.IOException;

public class CoreBungee extends Plugin {

    private static CoreBungee instance;
    private String serverName;
    private Channel server;
    private ConfigManager configManager;
    private ControlManager controlManager;

    @Override
    public void onEnable() {
        synchronized (this) {
            instance = this;
        }

        configManager = new ConfigManager(this);

        try {
            configManager.initConfig();
            configManager.loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

        controlManager = new ControlManager(this);
        controlManager.registerCoreCommands();
        controlManager.registerCoreEvents();

        serverName = new File(getDataFolder().getParentFile().getAbsolutePath()).getParentFile().getName();

        ConnectionManager.get().startClient();
    }

    public static synchronized CoreBungee get() {
        return instance;
    }

    public String getServerName() {
        return serverName;
    }

    public void sendPacket(PacketOut packet) {
        server.writeAndFlush(packet, server.voidPromise());
    }

    public void sendPackets(PacketOut... packets) {
        for (PacketOut p: packets) {
            server.write(p, server.voidPromise());
        }
        server.flush();
    }

    public void setCoreServer(Channel server) {
        this.server = server;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

}
