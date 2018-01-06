package org.kvlt.core;

import java.io.InputStream;

public class ResourceLoader {

    /**
     * Loads file from core jar
     */
    public static InputStream newResourceStream(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

}
