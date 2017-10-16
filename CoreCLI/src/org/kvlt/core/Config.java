package org.kvlt.core;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.kvlt.core.utils.Log;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class Config {

    private static final String FILE_NAME = "config.json";
    private static File config;
    private static Gson gson;
    private static JsonObject mysqlSet;
    public static HashMap<String, String> mysqlMap;

    public static void init() {
        try {
            config = new File(FILE_NAME);
            if (!config.exists()) {
                Log.$(createConfig()
                        ? "Создан новый файл конфигурации"
                        : "Файл конфигурации не создан."
                );
            }
            loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static void loadConfig() throws Exception {
        gson = new Gson();

        JsonReader reader = new JsonReader(new FileReader(FILE_NAME));
        JsonParser parser = new JsonParser();
        mysqlSet = parser.parse(reader).getAsJsonObject().getAsJsonObject("mysql");

        Type property = new TypeToken<HashMap<String, String>>(){}.getType();
        mysqlMap = gson.fromJson(mysqlSet.toString(), property);
    }

    public static String getMysqlConfig(String key) {
        return mysqlMap.get(key);
    }

    private static boolean createConfig() throws Exception {
        if (config.createNewFile()) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = Config.class.getClass().getResourceAsStream("/" + FILE_NAME);

            Files.copy(is, config.toPath());
            return true;
        }
        return false;
    }

}
