package org.kvlt.core.plugins;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.kvlt.core.utils.Log;

import java.io.*;
import java.nio.file.Files;

public class PluginConfig {

    public static final String PLUGINS_DIR_NAME = "plugins";
    private static final String DEFAULT_NAME = "config.json";

    private File file;
    private JsonObject json;
    private JsonParser jsonParser;
    private Gson gson;
    private FileWriter fileWriter;
    private JsonWriter jsonWriter;
    private FileReader fileReader;
    private JsonReader jsonReader;

    {
        json = new JsonObject();
        gson = new Gson();
        jsonParser = new JsonParser();
    }

    public JsonObject getJsonObject() {
        return json;
    }

    public boolean createFile(String dirName, String fileName) {
        try {
            file = new File(PLUGINS_DIR_NAME + "/" + dirName + "/" + fileName);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                if (file.createNewFile()) {
                    return false;
                }
            }

            fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            if (line == null || line.isEmpty()) {
                fileWriter = new FileWriter(file);
                jsonWriter = new JsonWriter(fileWriter);

                jsonWriter.beginObject();
                jsonWriter.endObject();
                jsonWriter.flush();
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean loadConfig() {
        try {
            JsonElement configContent = jsonParser
                    .parse(new JsonReader(new FileReader(file)));

            if (configContent.isJsonObject()) {
                json = configContent.getAsJsonObject();
                System.out.println(json);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveConfig() {
        try {
            FileWriter writer = new FileWriter(file);
            gson.toJson(json, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean copyDefault() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream is = classLoader.getResourceAsStream("./" + DEFAULT_NAME);
            Files.copy(is, file.toPath());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
