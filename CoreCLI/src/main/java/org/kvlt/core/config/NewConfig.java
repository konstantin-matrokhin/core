package org.kvlt.core.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class NewConfig {

    private final Yaml yaml;
    private final File file;

    private Map<String, Object> config;

    public NewConfig(String path) {
        yaml = new Yaml();
        file = new File(path);
    }

    public void load() {
        try {
            file.createNewFile();
            file.mkdirs();

            try (InputStream is = new FileInputStream(file)) {
                config = yaml.load(is);
                config.forEach((k, v) -> System.out.println(k + "|" + v));
            } catch (YAMLException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
