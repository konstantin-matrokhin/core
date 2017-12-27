package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.kvlt.core.bukkit.net.ConnectionManager;

public class ConnectCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.isOp()) {
            ConnectionManager.get().connect();
            return true;
        } else {
            commandSender.sendMessage("You don't have permission!");
        }
        return false;
    }
}
