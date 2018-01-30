package net.lastcraft.locale;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public enum Localization {
    RUSSIAN(0, "Русский", "§a100%", new Locale("ru")),
    ENGLISH(1, "English", "§c48%", new Locale("en")),
    UKRAINE(2, "Українська", "§c55%", new Locale("ua"));

    private int id;
    private String name;
    private Locale locale;
    private String percent;

    @Override
    public String toString() {
        return "Localization-" + getName();
    }

    private static Map<Integer, Localization> locales;
    private static Localization defaultLocalization;
    private static String error;

    public static Map<Integer, Localization> getLocales() {
        return locales;
    }

    public static Localization getDefaultLocalization() {
        return defaultLocalization;
    }

    public static Localization getLocale(int lang) {
        if (locales.containsKey(lang)) {
            return locales.get(lang);
        } else {
            return locales.get(defaultLocalization.getId());
        }
    }

    public static String getMessage(int lang, String key) {
        Localization localization = getLocale(lang);
        Locale locale = localization.getLocale();
        if (locale.getMessages().containsKey(key))
            return locale.getMessages().get(key);
        else {
            localization = getLocale(defaultLocalization.getId());
            locale = localization.getLocale();
            String message = locale.getMessages().get(key);
            return (message != null ? message : error);
        }
    }

    public static String getMessage(int lang, String key, Object... objects) {
        Localization localization = getLocale(lang);
        Locale locale = localization.getLocale();
        if (locale.getMessages().containsKey(key))
            return String.format(locale.getMessages().get(key), objects);
        else {
            localization = getLocale(defaultLocalization.getId());
            locale = localization.getLocale();
            String message = locale.getMessages().get(key);
            return String.format((message != null ? message : error), objects);
        }
    }

    public static List<String> getList(int lang, String key) {
        Localization localization = getLocale(lang);
        Locale locale = localization.getLocale();
        if (locale.getListMessages().containsKey(key)) {
            return locale.getListMessages().get(key);
        } else {
            localization = getLocale(defaultLocalization.getId());
            locale = localization.getLocale();
            List<String> messages = locale.getListMessages().get(key);
            return (messages != null ? messages : Collections.singletonList(error));
        }
    }

    public static List<String> getList(int lang, String key, Object... objects) {
        Localization localization = getLocale(lang);
        Locale locale = localization.getLocale();
        if (locale.getListMessages().containsKey(key)) {
            return format(locale.getListMessages().get(key), objects);
        } else {
            localization = getLocale(defaultLocalization.getId());
            locale = localization.getLocale();
            List<String> messages = locale.getListMessages().get(key);
            return format((messages != null ? messages : Collections.singletonList(error)), objects);
        }
    }

    private static List<String> format(List<String> list, Object... objects) {
        String string = list.stream().collect(Collectors.joining("±"));
        string = String.format(string, objects);
        return Arrays.asList(string.split("±"));
        //return Lists.newArrayList(string.split("±"));
    }

    Localization(int id, String name, String percent, Locale locale) {
        this.id = id;
        this.name = name;
        this.percent = percent;
        this.locale = locale;
    }

    public String getPercent() {
        return percent;
    }

    public Locale getLocale() {
        return locale;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    static {
        defaultLocalization = RUSSIAN;
        error = "§cNot found";
        locales = new ConcurrentHashMap<>();
        for (Localization locale : values()) {
            int id = locale.getId();
            if (!locales.containsKey(id))
                locales.put(id, locale);
        }
    }
}
