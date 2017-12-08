package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.plugins.CorePlugin;
import org.kvlt.core.utils.Printer;

import java.util.Set;

public class PluginsCommand extends Command {

    public PluginsCommand() {
        super("plugins");
        addAliases("pl", "plug", "addons");
    }

    @Override
    protected boolean execute() {
        Set<CorePlugin> plugins = CoreServer.get().getPluginManager().getPlugins();
        StringBuilder plList = new StringBuilder();
        plugins.forEach(p -> plList.append(p.getPluginData().getName()).append(" "));
        Printer.$("Плагины(" + plugins.size() + "):");
        Printer.$(plList);
        return true;
    }
}
