package org.kvlt.core.bungee;

import io.netty.channel.Channel;
import io.netty.util.internal.ConcurrentSet;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import org.kvlt.core.bungee.net.ConnectionManager;
import org.kvlt.core.bungee.packets.DisconnectPacket;
import org.kvlt.core.protocol.PacketOut;
import org.kvlt.core.protocol.PacketResolver;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class CoreBungee extends Plugin {

    private static CoreBungee instance;
    private String serverName;
    private Channel server;
    private Configuration config;
    private PacketResolver packetResolver;
    private ControlManager controlManager;
    private Set<String> premiumPlayers;

    @Override
    public void onEnable() {
        synchronized (this) {
            instance = this;
        }

        ConfigManager configManager = new ConfigManager(this);

        try {
            configManager.initConfig();
            configManager.loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

        config = configManager.getConfig();
        packetResolver = new PacketResolver();

        controlManager = new ControlManager(this);
        controlManager.registerCoreCommands();
        controlManager.registerCoreEvents();

        serverName = new File(getDataFolder().getParentFile().getAbsolutePath()).getParentFile().getName();

        String host = config.getString("db.host");
        int port = config.getInt("db.port");
        String username = config.getString("db.username");
        String password= config.getString("db.password");
        String db = config.getString("db.db");

        premiumPlayers = new ConcurrentSet<>();

        CoreDB.get().connect(host, port, username, password, db);

        ConnectionManager.get().startClient();
    }

    @Override
    public void onDisable() {
        ConnectionManager.get().setDisconnecting(true);
        DisconnectPacket dp = new DisconnectPacket();
        dp.send();
    }

    public static synchronized CoreBungee get() {
        return instance;
    }

    public ControlManager getControlManager() {
        return controlManager;
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

    public PacketResolver getPacketResolver() {
        return packetResolver;
    }

    public Configuration getConfig() {
        return config;
    }

    public Set<String> getPremiumPlayers() {
        return premiumPlayers;
    }

    public void setPremiumPlayers(Set<String> premiumPlayers) {
        this.premiumPlayers = premiumPlayers;
    }
}
