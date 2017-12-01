package org.kvlt.core.plugins;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.kvlt.core.utils.Log;
import org.kvlt.core.utils.LogType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class PluginLoader {

    private static final String PLUGINS_PATH = "./plugins/";

    private PluginManager pm;

    public PluginLoader(PluginManager pm) {
        this.pm = pm;
    }

    public void loadPlugins() {
        File dir = new File(PLUGINS_PATH);
        if (!dir.exists() || !dir.isDirectory()) {
            if (!dir.mkdir()) {
                Log.err("Не удалось создать папку plugins!");
                return;
            } else {
                Log.$("Создана папка plugins!");
            }
        }

        File[] plugins = parsePluginDir();
        if (plugins.length > 0) {
            for (File plugin : plugins) {
                loadJar(plugin);
            }
        } else {
            Log.warn("Ни одного плагина не найдено!");
        }
    }

    private void loadJar(File pluginFile) {
        try {
            JsonObject pluginJson = readPluginJson(pluginFile);
            String name = pluginJson.get("name").getAsString();
            String mainClass = pluginJson.get("main").getAsString();

            ClassLoader loader = new URLClassLoader(new URL[] {
                    pluginFile.toURI().toURL()
            });

            Class c = loader.loadClass(mainClass);
            CorePlugin p = (CorePlugin) c.newInstance();

            PluginData pd = new PluginData();
            pd.setName(name);
            pd.setMainClass(mainClass);
            p.setPluginData(pd);

            boolean added = pm.addPlugin(p);

            if (added) {
                //pm.loadPlugin(p);
            } else {
                Log.$(LogType.ERROR, "Плагин " + name + " не загружен!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JsonObject readPluginJson(File plugin) throws IOException {
        JarFile jar = new JarFile(plugin);
        ZipEntry entry = jar.getEntry("plugin.json");
        if (entry == null) {
            throw new IOException("plugins.json не найден в" + plugin.getName());
        }
        InputStream stream = jar.getInputStream(entry);
        JsonReader reader = new JsonReader(new InputStreamReader(stream));
        return new JsonParser().parse(reader).getAsJsonObject();
    }

    private File[] parsePluginDir() {
        File pluginsDir = new File(PLUGINS_PATH);
        return pluginsDir.listFiles(f -> f.getName().endsWith(".jar"));
    }

}
