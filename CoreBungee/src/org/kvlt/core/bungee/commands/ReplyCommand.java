package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.CoreBungee;
import org.kvlt.core.bungee.storages.ReplyStorage;
import org.kvlt.core.packets.player.PlayerMessagePacket;

public class ReplyCommand extends Command {

    public ReplyCommand() {
        super("r");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (args.length == 0) return;
        if (!(commandSender instanceof ProxiedPlayer)) return;

        ProxiedPlayer me = (ProxiedPlayer) commandSender;
        String myName = me.getName();
        String message = String.join(" ", args);
        String serverName = me.getServer().getInfo().getName();

        if (ReplyStorage.hasLastInterlocutor(myName)) {
            ProxiedPlayer interlocutor = ReplyStorage.getLastInterlocutor(myName);
            String interlocutorName = interlocutor.getName();
            PlayerMessagePacket pmp = new PlayerMessagePacket(serverName, myName, interlocutorName, message);

            CoreBungee.get().sendPacket(pmp);
        } else {
            me.sendMessage(new TextComponent("Некому отвечать."));
        }
    }
}
