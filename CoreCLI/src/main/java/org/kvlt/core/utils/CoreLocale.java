package org.kvlt.core.utils;

import com.google.gson.JsonObject;
import org.kvlt.core.config.Config;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.localization.LocaleConfig;
import org.kvlt.core.localization.Localization;

public class CoreLocale {

    private static Localization localization;

    public static void load() {
        localization = new Localization();
        JsonObject localeJson = Config.getCore("localization").getAsJsonObject();
        String url = localeJson.get("url").getAsString();

        localization.load(url, null);
    }

    public static String get(String lang, String key) {
        return localization.get(lang, key);
    }

    public static String get(String lang, String key, Object... format) {
        return localization.get(lang, key, format);
    }

    public static String get(ServerPlayer player, String key) {
        String lang = player.getLang();
        return localization.get(lang, key);
    }

    public static String get(ServerPlayer player, String key, Object... format) {
        String lang = player.getLang();
        return get(lang, key, format);
    }

    public static LocaleConfig getLocaleConfig(String language) {
        return localization.getLocale(language);
    }

}
