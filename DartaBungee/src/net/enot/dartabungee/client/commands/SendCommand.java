package net.enot.dartabungee.client.commands;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartasystem.packets.commands.SendCommandOutPacket;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 26.04.2017.
 */
public class SendCommand extends Command {

    public SendCommand() {
        super("send");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            commandSender.sendMessage(new TextComponent(MessageUtil.chat_no_connect_darta));
            return;
        }
        if (strings.length == 2){
            String server = strings[1];
            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(strings[0]);
            if (ProxyServer.getInstance().getServers().containsKey(server)){
                if (player != null){
                    player.connect(ProxyServer.getInstance().getServerInfo(server), (aBoolean, throwable) -> {
                        if (aBoolean){
                            commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§c" + player.getName() + " §6телепортирован на сервер §c" + server));
                            player.sendMessage(new TextComponent(MessageUtil.prefix + "§c" + commandSender.getName() + " §6телепортировал вас на сервер §c" + server));
                        } else {
                            commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§cНе удалось телепортировать " + player.getName() + " на сервер " + server));
                        }
                    });
                } else {
                    DartaBungee.getInstance().getDartaClient().write(new SendCommandOutPacket(commandSender.getName(), strings[0], server));
                }
            } else {
                commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§cСервер " + server + " не найден"));
            }
        } else {
            commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§cНеправильно указаны данные. Пишите /send <ник> <сервер>"));
        }
    }
}
