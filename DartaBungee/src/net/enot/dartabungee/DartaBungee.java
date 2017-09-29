package net.enot.dartabungee;

import net.enot.dartabungee.auth.AuthListener;
import net.enot.dartabungee.auth.AuthSQL;
import net.enot.dartabungee.auth.commands.*;
import net.enot.dartabungee.client.DartaClient;
import net.enot.dartabungee.client.commands.*;
import net.enot.dartabungee.client.listeners.ConnectionListener;
import net.enot.dartabungee.client.listeners.PingListener;
import net.enot.dartabungee.utils.ConfigUtil;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Енот on 23.04.2017.
 */
public class DartaBungee extends Plugin {

    private static DartaBungee instance;

    private DartaClient dartaClient;
    private AuthSQL authSQL;

    private Map<String, String> protectedAccounts = new HashMap<>();

    public boolean containsProtectedAcconts(String name){
        return protectedAccounts.containsKey(name.toLowerCase());
    }

    public boolean isProtected(String name, String ip){
        return protectedAccounts.get(name.toLowerCase()).equals(ip);
    }

    public DartaClient getDartaClient(){
        return dartaClient;
    }

    public AuthSQL getAuthSQL(){
        return authSQL;
    }

    public static DartaBungee getInstance(){
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

        authSQL = new AuthSQL("Auth");

        createConfigs();
        loadConfigs();
        registerListeners();
        registerCommands();
    }

    public void onDisable(){
        dartaClient.close();
    }

    private void registerCommands(){
        PluginManager pluginManager = getProxy().getPluginManager();

        // Client Commands
        pluginManager.registerCommand(this, new ServerCommand());
        pluginManager.registerCommand(this, new AlertCommand());
        pluginManager.registerCommand(this, new FindCommand());
        pluginManager.registerCommand(this, new OnlineCommand("online"));
        pluginManager.registerCommand(this, new OnlineCommand("glist"));
        pluginManager.registerCommand(this, new WhereCommand("whe"));
        pluginManager.registerCommand(this, new WhereCommand("where"));
        pluginManager.registerCommand(this, new SendCommand());

        //Auth Commands
        pluginManager.registerCommand(this, new LoginCommand("login"));
        pluginManager.registerCommand(this, new LoginCommand("l"));
        pluginManager.registerCommand(this, new RegisterCommand("register"));
        pluginManager.registerCommand(this, new RegisterCommand("reg"));
        pluginManager.registerCommand(this, new LogoutCommand());
        pluginManager.registerCommand(this, new LicenseCommand());
        pluginManager.registerCommand(this, new UnlicenseCommand());
        pluginManager.registerCommand(this, new ChangePasswordCommand("changepassword"));
        pluginManager.registerCommand(this, new ChangePasswordCommand("changepw"));
        pluginManager.registerCommand(this, new EmailCommand());
        pluginManager.registerCommand(this, new CodeCommand());
        pluginManager.registerCommand(this, new PasswordCommand());
    }

    private void registerListeners(){
        new ConnectionListener();
        new PingListener();
        new AuthListener();
    }


    private void createConfigs() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        ConfigUtil.createConfig("auth.yml");
    }



    private void loadConfigs(){
        loadAuthConfig();
    }

    private void loadAuthConfig(){
        try {
            Configuration config = ConfigUtil.getConfiguration("auth.yml");

            for (String protect : config.getStringList("ProtectedAccounts")){
                String[] strings = protect.split(":");
                protectedAccounts.put(strings[0].toLowerCase(), strings[1]);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
