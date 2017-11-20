package org.kvlt.core.bungee.entities.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.net.ConnectionManager;

public class ConnectCommand extends Command {

    public ConnectCommand() {
        super("con");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ConnectionManager.get().connect();
    }

}
