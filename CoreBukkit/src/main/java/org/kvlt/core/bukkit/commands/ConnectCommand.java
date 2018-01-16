package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.kvlt.core.bukkit.net.ConnectionManager;

public class ConnectCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        String response;

        if (sender.isOp()) {
            if (!ConnectionManager.get().isConnected()) {
                ConnectionManager.get().connect();
                response = "Подключаюсь к кору..";
            } else {
                response = "Подключение уже установлено.";
            }
            sender.sendMessage(response);
            return true;
        } else {
            sender.sendMessage("You don't have permission!");
        }
        return false;
    }
}
