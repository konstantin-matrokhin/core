package org.kvlt.core.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.packets.bukkit.ServerCommandPacketOld;

import java.util.Arrays;

public class SendCommandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length < 2) return false;

        String to = args[0];
        String[] cmdArr = Arrays.copyOfRange(args, 1, args.length);
        String cmd = String.join(" ", cmdArr);

        ServerCommandPacketOld scp = new ServerCommandPacketOld(commandSender.getName(), to, cmd);
        CorePlugin.get().getCoreServer().writeAndFlush(scp);
        return true;
    }
}
