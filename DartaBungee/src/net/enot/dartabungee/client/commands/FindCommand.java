package net.enot.dartabungee.client.commands;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartasystem.packets.commands.FindCommandOutPacket;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 24.04.2017.
 */
public class FindCommand extends Command {

    public FindCommand() {
        super("find");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            commandSender.sendMessage(new TextComponent(MessageUtil.chat_no_connect_darta));
            return;
        }
        if (strings.length == 1){
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(strings[0]);
            if (player != null){
                commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§c" + player.getName() + " §6находится на сервере §c" + player.getServer().getInfo().getName()));
            } else {
                DartaBungee.getInstance().getDartaClient().write(new FindCommandOutPacket(commandSender.getName(), strings[0]));
            }
        } else {
            commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§cНеправильно указаны данные. Пишите /find <ник>"));
        }
    }
}
