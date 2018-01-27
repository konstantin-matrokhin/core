package org.kvlt.core.commands;

import org.kvlt.core.Core;
import org.kvlt.core.config.Config;
import org.kvlt.core.email.Email;
import org.kvlt.core.plugins.CorePlugin;
import org.kvlt.core.utils.Localization;
import org.kvlt.core.utils.Log;

public class ReloadCommand extends Command {

    public ReloadCommand() {
        super("reload");
    }

    @Override
    protected boolean execute() {
        Log.$("Перезагружаю все плагины и конфиги...");
        Core.get().getPluginManager().getPlugins().forEach(CorePlugin::onDisable);
        Core.get().getPluginManager().getPlugins().clear();
        Core.get().getPluginLoader().loadPlugins();

        Config.init();
        Localization.load();
        Email.init();

        Log.$("Перезагрузка завершена!");
        return true;
    }
}
