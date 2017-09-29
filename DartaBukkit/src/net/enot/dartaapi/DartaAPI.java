package net.enot.dartaapi;

import net.enot.dartaapi.client.DartaClient;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Енот on 27.04.2017.
 */
public class DartaAPI extends JavaPlugin {

    private static DartaAPI instance;

    private DartaClient dartaClient;

    public DartaClient getDartaClient(){
        return dartaClient;
    }

    public static DartaAPI getInstance(){
        return instance;
    }

    public void onLoad(){
        instance = this;
    }

    public void onEnable(){
        try {
            dartaClient = new DartaClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
