package org.kvlt.core.plugins;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.kvlt.core.utils.Log;

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
        File[] plugins = parsePluginDir();
        for (File plugin: plugins) {
            loadJar(plugin);
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
                pm.loadPlugin(p);
            }

            Log.$(c.getSimpleName() + " -- name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JsonObject readPluginJson(File plugin) throws IOException {
        JarFile jar = new JarFile(plugin);
        ZipEntry entry = jar.getEntry("plugin.json");
        InputStream stream = jar.getInputStream(entry);
        JsonReader reader = new JsonReader(new InputStreamReader(stream));
        JsonObject json = new JsonParser().parse(reader).getAsJsonObject();
        return json;
    }

    private File[] parsePluginDir() {
        File pluginsDir = new File(PLUGINS_PATH);
        return pluginsDir.listFiles(f -> f.getName().endsWith(".jar"));
    }

}
