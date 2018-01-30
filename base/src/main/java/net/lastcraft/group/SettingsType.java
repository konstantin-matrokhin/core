package net.lastcraft.group;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum SettingsType {
    LANGUAGE(0),
    HIDER(1),
    CHAT(2),
    FLY(3),
    BLOOD(4),
    MUSIC(5),
    BOARD(6),
    HOLOCHAT(7);

    private static Map<Integer, SettingsType> settingTypes;

    public static SettingsType getSettingType(int key) {
        if (key > settingTypes.size()) {
            throw new IllegalArgumentException("key не может быть больше - " + settingTypes.size());
        }
        return settingTypes.get(key);
    }

    public static Map<Integer, SettingsType> getSettingTypes() {
        return settingTypes;
    }

    private int key;

    SettingsType(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    static {
        settingTypes = new ConcurrentHashMap<>();
        for (SettingsType settingsType : values()) {
            if (!settingTypes.containsKey(settingsType.getKey()))
                settingTypes.put(settingsType.getKey(), settingsType);
        }
    }
}
