package org.kvlt.core.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.kvlt.core.utils.Log;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.HashMap;

public class ConfigManager {

    private String fileName;
    private File config;
    private Gson gson;
    private JsonParser parser;

    public void init(String fileName) {
        this.fileName = fileName;

        try {
            config = new File(fileName);
            gson = new Gson();
            parser = new JsonParser();
            if (!config.exists()) {
                Log.$(createConfig()
                        ? "Создан новый файл конфигурации"
                        : "Файл конфигурации не создан."
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public HashMap<String, String> loadSection(String section) throws Exception {
        JsonObject jsonSection = parser.parse(new JsonReader(new FileReader(fileName))).getAsJsonObject().getAsJsonObject(section);
        Type property = new TypeToken<HashMap<String, String>>(){}.getType();
        return gson.fromJson(jsonSection.toString(), property);
    }

    public String getValue(HashMap<String, String> map, String key) {
        String val = map.get(key);
        if (val == null) {
            throw new NullPointerException("Вероятно, поле " + key + " отсутсвует в конфиге.");
        }
        return val;
    }

    //TODO: Fix
    public boolean createConfig() throws Exception {
        if (config.createNewFile()) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = Config.class.getClass().getResourceAsStream("/" + fileName);

            Files.copy(is, config.toPath());
            return true;
        }
        return false;
    }

}
