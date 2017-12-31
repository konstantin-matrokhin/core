package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.net.ConnectionManager;

public class ReconnectCommand extends Command {

    public ReconnectCommand() {
        super("reconnect", null, "cc", "core", "rc");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ConnectionManager.get().connect();
    }

}
