package net.enot.dartasystem.utils;

import net.enot.dartasystem.DartaSystem;

import java.io.File;

/**
 * Created by Енот on 23.04.2017.
 */
public class DartaSystemUtil {

    public static String getPath(){
        File currentJavaJarFile = new File(DartaSystem.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String currentJavaJarFilePath = currentJavaJarFile.getAbsolutePath();
        return currentJavaJarFilePath.replace(currentJavaJarFile.getName(), "");
    }

    public static boolean isWindows() {
        return (System.getProperty("os.name").toLowerCase().contains("win"));
    }

    public static String getFolder(){
        String[] folders = isWindows() ? getPath().split("\\\\") : getPath().split("/");
        return folders[folders.length-2];
    }
}
