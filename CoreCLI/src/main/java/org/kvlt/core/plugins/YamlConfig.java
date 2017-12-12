package org.kvlt.core.plugins;

import org.yaml.snakeyaml.Yaml;

import java.io.*;

public class YamlConfig {

    public static final String PLUGINS_DIR_NAME = "plugins";
    private static final String DEFAULT_NAME = "config.yaml";

    private static Yaml yaml;

    private File file;
    private FileReader fileReader;

    static {
        yaml = new Yaml();
    }

    public YamlConfig(CorePlugin plugin, String fileName) {
        try {
            String dir = plugin.getPluginData().getName();
            file = new File(PLUGINS_DIR_NAME +
                    File.pathSeparator + dir + File.pathSeparator + fileName);
            file.getParentFile().mkdirs();
            fileReader = new FileReader(file);

            if (!file.exists()) {
                if (file.createNewFile()) {
                    yaml.load(fileReader);
                } else {
                    throw new IOException(String.format("Не удалось создать конфиг %s(%s)",
                            dir,
                            fileName));
                }
            } else {
                yaml.load(fileReader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public YamlConfig(CorePlugin plugin) {
        this(plugin, DEFAULT_NAME);
    }


}
