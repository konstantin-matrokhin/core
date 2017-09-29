package net.enot.dartabungee.utils;

import net.enot.dartabungee.DartaBungee;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Created by Енот on 01.05.2017.
 */
public class ConfigUtil {

    public static void createConfig(String fileName){
        File file = new File(DartaBungee.getInstance().getDataFolder(), fileName);

        if (!file.exists()) {
            try {
                InputStream in = DartaBungee.getInstance().getResourceAsStream(fileName);
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Configuration getConfiguration(String fileName) throws IOException {
        return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(DartaBungee.getInstance().getDataFolder(), fileName));
    }
}
