package org.kvlt.core.commands;

import org.kvlt.core.Core;
import org.kvlt.core.plugins.CorePlugin;
import org.kvlt.core.utils.Log;

public class ReloadCommand extends Command {

    public ReloadCommand() {
        super("reload");
    }

    @Override
    protected boolean execute() {
        Log.$("Перезагружаю все плагины...");
        Core.get().getPluginManager().getPlugins().forEach(CorePlugin::onDisable);
        Core.get().getPluginManager().getPlugins().clear();
        Core.get().getPluginLoader().loadPlugins();
        Log.$("Плагины перезагружены!");
        return true;
    }
}
