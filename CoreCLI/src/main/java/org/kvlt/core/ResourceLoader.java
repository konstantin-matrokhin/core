package org.kvlt.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ResourceLoader {

    /**
     * Loads file from root
     */
    public static InputStream newResourceStream(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            System.out.println(classLoader.getResources("./").nextElement().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classLoader.getResourceAsStream("./" + fileName);
    }

}
