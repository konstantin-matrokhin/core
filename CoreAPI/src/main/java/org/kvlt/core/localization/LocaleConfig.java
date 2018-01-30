package org.kvlt.core.localization;

import java.io.InputStream;
import java.io.Writer;

public class LocaleConfig extends YamlConfig {

    private final String name;

    public LocaleConfig(String name, InputStream in, Writer out) {
        super(in, out);
        this.name = name;
    }

    public void add(String key, String value) {

    }

    public String getName() {
        return name;
    }

}
