package net.lastcraft.locale;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Locale {
    private String name;

    public Locale(String name) {
        this.name = name;
        //try {
        //    //loadFromSite();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
    }

    public Map<String, String> getMessages() {
        return this.messages;
    }

    private final Map<String, String> messages = new ConcurrentHashMap<>();
    private final Map<String, List<String>> listMessages = new ConcurrentHashMap<>();

    public Map<String, List<String>> getListMessages() {
        return this.listMessages;
    }

    protected void add(String key, String message) {
        if (this.messages.containsKey(key)) {
            throw new IllegalArgumentException("ОШИБКА!!! Ключ " + key + " " + name + " уже есть в данной локализации");
        } else {
            this.messages.put(key, message);
        }
    }

    protected void add(String key, List<String> message) {
        if (this.listMessages.containsKey(key)) {
            throw new IllegalArgumentException("ОШИБКА!!! Ключ " + key + " " + name + " уже есть в данной локализации");
        } else {
            this.listMessages.put(key, message);
        }
    }

    //private void loadFromSite() throws IOException {
    //    URL oracle = new URL("http://s1" + Connection.domane + "/lang/" + name + ".yml");
    //    FileConfiguration config = YamlConfiguration.loadConfiguration(new InputStreamReader(oracle.openStream()));
    //    for (String string : config.getConfigurationSection("").getKeys(false)) {
    //        if (config.getList(string) == null && config.get(string) == null) {
    //            System.out.println("ОШИБКА! Файла нет или он заполнен не правильно!");
    //        } else {
    //            if(!config.isList(string)) {
    //                add(string, config.getString(string));
    //            } else {
    //                add(string, config.getStringList(string));
    //            }
    //        }
    //    }
    //}

}
