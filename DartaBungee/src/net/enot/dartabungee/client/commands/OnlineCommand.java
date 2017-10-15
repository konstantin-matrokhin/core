package net.enot.dartabungee.client.commands;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartabungee.client.listeners.PingListener;
import net.enot.dartasystem.packets.commands.OnlineCommandOutPacket;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 24.04.2017.
 */
public class OnlineCommand extends Command {

    public OnlineCommand(String command) {
        super(command);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            commandSender.sendMessage(new TextComponent(MessageUtil.chat_no_connect_darta));
            return;
        }
        if (strings.length == 0){
            commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§6Онлайн со всех серверов - §c" + PingListener.online));
        } else if (strings.length == 1){
            DartaBungee.getInstance().getDartaClient().write(new OnlineCommandOutPacket(commandSender.getName(), strings[0]));
        } else {
            commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§cНеправильно указаны данные. Пишите /online или /online <сервер>"));
        }
    }
}
