package net.lastcraft.gamer.data;

import net.lastcraft.gamer.GamerBase;
import net.lastcraft.group.SettingsType;
import net.lastcraft.locale.Localization;
import net.lastcraft.sql.GlobalLoader;
import net.lastcraft.util.Pair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SettingsSection extends Section {
    private int lang;
    private Map<SettingsType, Boolean> settings;

    public SettingsSection(GamerBase baseGamer) {
        super(baseGamer);
    }

    @Override
    boolean loadData() {
        lang = Localization.getDefaultLocalization().getId();
        settings = new ConcurrentHashMap<>();
        for (Pair pair : GlobalLoader.getSettings(getBaseGamer().getPlayerID())) {
            int type = (int) pair.getFirst();
            boolean value = ((int) pair.getSecond() == 1);
            SettingsType settingsType = SettingsType.getSettingType(type);

            if (settingsType == SettingsType.LANGUAGE) {
                int newLang = (int) pair.getSecond();
                if (Localization.getLocale(newLang) != null) {
                    this.lang = newLang;
                }

                continue;
            }

            if (settingsType == SettingsType.FLY && getBaseGamer().getGroup().getLevel() < 2) {
                settings.put(settingsType, false);
                continue;
            }
            if (settingsType == SettingsType.MUSIC && getBaseGamer().getGroup().getLevel() < 3) {
                settings.put(settingsType, false);
                continue;
            }
            settings.put(settingsType, value);
        }

        settings.putIfAbsent(SettingsType.CHAT, true);
        settings.putIfAbsent(SettingsType.BOARD, true);

        if (getBaseGamer().getGroup().getLevel() >= 2 && !settings.containsKey(SettingsType.FLY)) {
            settings.put(SettingsType.FLY, true);
        }
        if (getBaseGamer().getGroup().getLevel() >= 3 && !settings.containsKey(SettingsType.MUSIC)) {
            settings.put(SettingsType.MUSIC, true);
        }
        return true;
    }

    public int getLang() {
        return lang;
    }

    public boolean getSetting(SettingsType type) {
        for (SettingsType settingsType : settings.keySet()) {
            if (settingsType.getKey() == type.getKey()) {
                return settings.get(type);
            }
        }
        return false;
    }

    public void setSetting(SettingsType type, boolean value) {
        for (SettingsType settingsType : SettingsType.values()) {
            if (settingsType.getKey() == type.getKey()) {
                settings.remove(type);
                settings.put(type, value);
                save(type.getKey(), (value ? 1 : 0));
                return;
            }
        }
    }

    public void setLang(int lang) {
        this.lang = lang;
        save(SettingsType.LANGUAGE.getKey(), lang);
    }

    private void save(int type, int value) {
        int playerID = getBaseGamer().getPlayerID();
        GlobalLoader.setSetting(playerID, type, value);
    }
}