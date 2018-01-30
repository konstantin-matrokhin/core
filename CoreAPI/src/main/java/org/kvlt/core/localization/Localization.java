package org.kvlt.core.localization;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Localization {

    private static final String LOCALES_LIST = "list.yml";

    private final Map<String, LocaleConfig> langMap;

    {
        langMap = new HashMap<>();
    }

    public void load(String url, Writer writer) {
        String listPath = url + "/" + LOCALES_LIST;
        try {
            InputStream listStream = getStreamFromPath(listPath);
            YamlConfig langList = new YamlConfig(listStream);
            List<String> languages = langList.getList(String.class, "languages");
            System.out.println(String.format("Найдено локализаций: %d.", languages.size()));

            languages.stream().parallel().forEach((lang) -> {
                try {
                    InputStream langStream = getStreamFromPath(url + lang + ".yml");
                    LocaleConfig langConfig = new LocaleConfig(lang, langStream, writer);
                    langMap.put(lang, langConfig);
                    System.out.println(String.format("Локализация %s успешно загружена!", lang));
                } catch (IOException e) {
                    System.out.println(String.format("Локализация %s не загружена: ", lang));
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private InputStream getStreamFromPath(String path) throws IOException {
        InputStream in;
        URL url = new URL(path);

        if (path.startsWith("file")) {
            URLConnection con = url.openConnection();
            return con.getInputStream();
        } else if (path.startsWith("http")) {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(3000);
            con.setConnectTimeout(3000);
            con.connect();
            return con.getInputStream();
        } else {
            throw new IllegalArgumentException("Illegal protocol! Must be http(s):// or file:///");
        }
    }

    public String get(String lang, String key, Object... format) {
        return get(lang, String.format(key, format));
    }

    public String get(String lang, String key) {
        if (langMap.get(lang).getString(key) == null) {
            System.out.println(String.format("Отсутсвует ключ в локализации: {KEY = \"%s\", LANG = %s}",
                    key, lang));
            lang = "ru";
        }
        if (!langMap.containsKey(lang)) {
            return String.format("Локализация %s не загружена", lang);
        }
        return langMap.get(lang).getString(key);
    }

    public LocaleConfig getLocale(String lang) {
        LocaleConfig locale = langMap.get(lang);
        Objects.requireNonNull(locale, String.format("Locale %s is null!", lang));
        return locale;
    }

}
