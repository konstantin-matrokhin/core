package org.kvlt.core.bungee;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigManager {

    private Plugin plugin;
    private Configuration config;
    private File dataFolder;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        dataFolder = plugin.getDataFolder();
    }

    public void loadConfig() throws IOException {
        config = ConfigurationProvider
                .getProvider(YamlConfiguration.class)
                .load(new File(dataFolder, "config.yml"));
    }

    public void initConfig() throws IOException {
        File file = new File(dataFolder, "config.yml");

        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        if (!file.exists()) {
            Files.copy(plugin.getResourceAsStream("config.yml"), file.toPath());
        }
    }

    public void saveConfig() throws IOException {
        ConfigurationProvider
                .getProvider(YamlConfiguration.class)
                .save(config, new File(dataFolder, "config.yml"));
    }

    public Configuration getConfig() {
        return config;
    }

}
