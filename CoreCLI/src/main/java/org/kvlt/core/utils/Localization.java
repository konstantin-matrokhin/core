package org.kvlt.core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.kvlt.core.config.Config;
import org.kvlt.core.entities.ServerPlayer;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Localization {

    private static final Map<String, CoreConfig> langMap;

    static {
        langMap = new HashMap<>();
    }

    public static void load() {
        JsonObject json = Config.getCore("localization").getAsJsonObject();
        JsonObject arrayJson = json.getAsJsonObject("languages");
        String address = json.get("url").getAsString();
        String method = json.get("method").getAsString();

        Log.$(String.format("Загрузка локализаций %s(%s)", method, address));

        Map<String, JsonObject> langArray = parseLangArray(arrayJson);
        langArray.forEach((langName, obj) -> {
            String fileName = obj.get("file").getAsString();
            try {
                Log.$("Загрузка: " + fileName + "..");
                URL url = new URL(address + "/" + fileName);
                if (address.startsWith("file")) {
                    URLConnection con = url.openConnection();
                    load(langName, con.getInputStream());
                } else {
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setConnectTimeout(5000);
                    con.setReadTimeout(5000);
                    con.setRequestMethod(method);
                    con.connect();
                    load(langName, con.getInputStream());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static Map<String, JsonObject> parseLangArray(JsonObject json) {
        String strJson = json.toString();
        Type arrayType = new TypeToken<HashMap<String, JsonObject>>(){}.getType();
        Map<String, JsonObject> langArray = new Gson()
                .fromJson(strJson, arrayType);

        return langArray;
    }

    public static void load(String lang, InputStream in) {
        try {
            langMap.put(lang, new YAMLConfig(in));
            Log.$(String.format("Локализация %s загружена", lang));
        } catch (Exception e) {
            Log.err(String.format("Возникла ошибка при загрузки локализцаии %s: %s",
                    lang, e.getLocalizedMessage()));
        }
    }

    public static String get(ServerPlayer player, String key) {
        String lang;
        if (player == null) {
            lang = LangCommons.RUSSAIN;
        } else {
            lang = player.getLang();
        }
        return get(lang, key);
    }

    public static String get(ServerPlayer player, String key, Object... format) {
        return get(player, String.format(key, format));
    }

    public static String get(String lang, String key) {
        if (langMap == null
                || langMap.get(lang) == null
                || langMap.get(lang).getString(key) == null
                || langMap.size() == 0) {
            //TODO replace with LangCommons
            return String.format("Localization error! Please, contact us to solve the issue.\n" +
                    "KEY = %s\n" +
                    "LANG = %s",
                    key, lang);
        }
        return langMap.get(lang).getString(key);
    }

}
