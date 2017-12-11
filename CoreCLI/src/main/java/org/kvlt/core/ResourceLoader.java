package org.kvlt.core;

import java.io.InputStream;
import java.nio.file.Files;

public class ResourceLoader {

    /**
     * Loads file from root
     */
    public static InputStream newResourceStream(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream("./" + fileName);
    }

}
