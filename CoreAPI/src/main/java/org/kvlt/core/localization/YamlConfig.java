package org.kvlt.core.localization;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.io.Writer;
import java.util.*;

public class YamlConfig {

    private Yaml yaml = new Yaml();;
    private InputStream in;
    private Writer out = null;

    private Map<String, Object> map;

    public YamlConfig(InputStream in) {
        this.in = in;
        reload();
    }

    public YamlConfig(InputStream in, Writer out) {
        this.in = in;
        this.out = out;
        reload();
    }

    public void reload() {
        map = yaml.load(in);
    }

    public void save() {
        yaml.dump(map, out);
    }

    public String getString(String key) {
        return (String) map.get(key);
    }

    public void set(String key, Object value) {
        if (!map.containsKey(key)) {
            map.put(key, value);
        }
    }

    public void setString(String key, String value) {
        map.put(key, value);
    }

    public void setList(String key, List list) {
        map.put(key, list);
    }

    public <T> List<T> getList(Class<T> type, String key) {
        if (map.get(key) == null) {
            return Collections.singletonList((T) Localization.ERROR);
        }
        return (ArrayList<T>) map.get(key);
    }

}
