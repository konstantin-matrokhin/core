package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.plugins.CorePlugin;
import org.kvlt.core.utils.Log;

public class ReloadCommand extends Command {

    public ReloadCommand() {
        super("reload");
    }

    @Override
    protected boolean execute() {
        Log.$("Перезагружаю все плагины...");
        CoreServer.get().getPluginManager().getPlugins().forEach(CorePlugin::onDisable);
        CoreServer.get().getPluginManager().getPlugins().clear();
        CoreServer.get().getPluginLoader().loadPlugins();
        Log.$("Плагины перезагружены!");
        return true;
    }
}
