package org.kvlt.core.utils;

import org.kvlt.core.config.Config;
import org.kvlt.core.entities.ServerPlayer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Localization {

    private static final Map<Lang, CoreConfig> langMap;

    static {
        langMap = new HashMap<>();
    }

    public static void loadFromURL(Lang lang) {
        boolean fromURL = Config.getCore("localization-from").equalsIgnoreCase("url");

        if (fromURL) {
            try {
                String address = Config.getCore("localization-address");
                URL url = new URL(address);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                load(Lang.RUSSIAN, con.getInputStream());

//                BufferedReader reader= new BufferedReader(new InputStreamReader(con.getInputStream()));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//                reader.close();
//
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void load(Lang lang, InputStream in) {
        try {
            langMap.put(lang, new YAMLConfig(in));
            Log.$(String.format("Локализация %s загружена", lang));
        } catch (Exception e) {
            Log.err(String.format("Возникла ошибка при загрузки локализцаии %s: %s",
                    lang, e.getLocalizedMessage()));
        }
    }

    public static String get(ServerPlayer player, String key) {
        Lang lang;
        if (player == null) {
            lang = Lang.RUSSIAN;
        } else {
            lang = player.getLang();
        }
        return get(player.getLang(), key);
    }

    public static String get(ServerPlayer player, String key, Object... format) {
        return get(player, String.format(key, format));
    }

    public static String get(Lang lang, String key) {
        if (langMap == null || langMap.get(lang).getString(key) == null || langMap.size() == 0) {
            //TODO replace with LangCommons
            return String.format("Localization error! Please, contact us to solve the issue.\n" +
                    "KEY = %s\n" +
                    "LANG = %s",
                    key, lang);
        }
        return langMap.get(lang).getString(key);
    }

}
