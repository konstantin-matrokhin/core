package net.enot.dartabungee.client.commands;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartasystem.packets.commands.AlertCommandPacket;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 24.04.2017.
 */
public class AlertCommand extends Command {

    public AlertCommand() {
        super("alert");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            commandSender.sendMessage(new TextComponent(MessageUtil.chat_no_connect_darta));
            return;
        }
        if (strings.length > 0){
            String message = "";
            for (String text : strings){
                message += text.replace('&', '§') + " ";
            }
            message = message.substring(0, message.length()-1);
            DartaBungee.getInstance().getDartaClient().write(new AlertCommandPacket(message));
            ProxyServer.getInstance().broadcast(new TextComponent(message));
        } else {
            commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§cНедостаточно данных. Пишите /alert <сообщение>"));
        }
    }

}
