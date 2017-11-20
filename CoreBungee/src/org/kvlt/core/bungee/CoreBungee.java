package org.kvlt.core.bungee;

import io.netty.channel.Channel;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.kvlt.core.bungee.entities.commands.PingCommand;
import org.kvlt.core.bungee.net.ConnectionManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CoreBungee extends Plugin {

    private static CoreBungee instance;
    private Configuration config;
    private String serverName;
    private Channel server;
    private PingEventListener pingEventListener;

    @Override
    public void onEnable() {
        synchronized (this) {
            instance = this;
        }

        try {
            initConfig();
            loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }

        serverName = new File(getDataFolder().getParentFile().getAbsolutePath()).getParentFile().getName();
        pingEventListener = new PingEventListener();

        ConnectionManager.get().startClient();

        getProxy().getPluginManager().registerListener(this, new ProxyEventListener());
        getProxy().getPluginManager().registerListener(this, pingEventListener);
        getProxy().getPluginManager().registerCommand(this, new PingCommand());

    }

    @Override
    public void onDisable() {
        //
    }

    private void loadConfig() throws IOException {
        config = ConfigurationProvider
                .getProvider(YamlConfiguration.class)
                .load(new File(getDataFolder(), "config.yml"));
    }

    private void initConfig() throws IOException {
        File file = new File(getDataFolder(), "config.yml");

        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        if (!file.exists()) {
            Files.copy(getResourceAsStream("config.yml"), file.toPath());
        }
    }

    public void saveConfig() throws IOException {
        ConfigurationProvider
                .getProvider(YamlConfiguration.class)
                .save(config, new File(getDataFolder(), "config.yml"));
    }

    public Configuration getConfig() {
        return config;
    }

    public PingEventListener getPingEventListener() {
        return pingEventListener;
    }

    public static synchronized CoreBungee get() {
        return instance;
    }

    public String getServerName() {
        return serverName;
    }

    public Channel getCoreServer() {
        return server;
    }

    public void setCoreServer(Channel server) {
        this.server = server;
    }
}
