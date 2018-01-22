package org.kvlt.core.config;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
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

/**
 * Класс, позволяющий создавать конфиги
 */
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

    public HashMap<String, JsonElement> loadSection(String section) throws Exception {
        JsonElement jsonSection = parser.parse(
                new JsonReader(new FileReader(fileName)))
                    .getAsJsonObject()
                    .getAsJsonObject(section);

        Type property = new TypeToken<HashMap<String, JsonElement>>(){}.getType();
        Log.$("Конфигурация " + section + " загружена");
        return gson.fromJson(jsonSection, property);
    }

    public JsonElement getValue(HashMap<String, JsonElement> map, String key) {
        JsonElement val = map.get(key);
        if (val == null) {
            throw new NullPointerException("Вероятно, поле " + key + " отсутсвует в конфиге.");
        }
        return val;
    }

    public boolean createConfig() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        Files.copy(is, config.toPath());
        return true;
    }

}
