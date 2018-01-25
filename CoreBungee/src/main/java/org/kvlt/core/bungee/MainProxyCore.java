package org.kvlt.core.bungee;

import io.netty.channel.Channel;
import io.netty.util.internal.ConcurrentSet;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import org.kvlt.core.bungee.net.ConnectionManager;
import org.kvlt.core.protocol.PacketOut;
import org.kvlt.core.protocol.PacketResolver;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public final class MainProxyCore implements ProxyCore {

    private String serverName;
    private Channel server;
    private Configuration config;
    private PacketResolver packetResolver;
    private Set<String> premiumPlayers;
    private ConnectionManager connectionManager;
    private ControlManager controlManager;

    MainProxyCore(Plugin plugin) {
        ConfigManager configManager = new ConfigManager(plugin);

        try {
            configManager.initConfig();
            configManager.loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connectionManager = new ConnectionManager();
        config = configManager.getConfig();
        packetResolver = new PacketResolver();

        controlManager = new ControlManager(plugin);
        controlManager.registerCoreCommands();
        controlManager.registerCoreEvents();

        serverName = new File(plugin.getDataFolder().getParentFile().getAbsolutePath()).getParentFile().getName();

        String host = config.getString("db.host");
        int port = config.getInt("db.port");
        String username = config.getString("db.username");
        String password= config.getString("db.password");
        String db = config.getString("db.db");

        premiumPlayers = new ConcurrentSet<>();

        CoreDB.get().connect(host, port, username, password, db);
    }

    @Override
    public void connect() {
        connectionManager.startClient();
    }

    @Override
    public void disconnect() {
        connectionManager.disconnect();
    }

    @Override
    public void sendPacket(PacketOut packet) {
        Channel channel = connectionManager.getChannel();
        channel.writeAndFlush(packet, channel.voidPromise());
    }

    @Override
    public Channel getServerChannel() {
        return connectionManager.getChannel();
    }

    @Override
    public PacketResolver getPacketResolver() {
        return packetResolver;
    }

    @Override
    public Set<String> getPremiumPlayers() {
        return premiumPlayers;
    }

    @Override
    public String getProxyName() {
        return serverName;
    }

    @Override
    public void setProxyName(String name) {
        serverName = name;
    }

    @Override
    public Configuration getConfig() {
        return config;
    }

    @Override
    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    @Override
    public ControlManager getControlManager() {
        return controlManager;
    }

}
