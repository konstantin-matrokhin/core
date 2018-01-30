package org.kvlt.core.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YAMLConfig implements CoreConfig {

    private Yaml yaml;
    private InputStream in;
    private Map<String, Object> map;

    {
        yaml = new Yaml();
    }

    public YAMLConfig(InputStream in) {
        this.in = in;
        reload();
    }

    @Override
    public void reload() {
        map = yaml.load(in);
    }

    @Override
    public String getString(String key) {
        return (String) map.get(key);
    }

    //TODO get list

}