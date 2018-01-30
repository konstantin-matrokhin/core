package org.kvlt.core.bungee.utils;

import net.md_5.bungee.config.Configuration;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.localization.Localization;

@Deprecated
public class BungeeLocale {

    private static Localization localization;

    public static void load() {
        Configuration config = CoreBungee.getAPI().getConfig();
        String url = config.getString("localization-url");

        if (url == null || url.isEmpty()) {
            url = "http://s1.huesos228.com/lang";
        }

        localization = new Localization();
        localization.load(url, null);
    }

    public static String get(String lang, String key) {
        return localization.get(lang, key);
    }

    public static String get(String lang, String key, Object... format) {
        return localization.get(lang, key, format);
    }

}
