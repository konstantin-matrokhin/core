package net.enot.dartabungee.client.commands;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 24.04.2017.
 */
public class ServerCommand extends Command {

    public ServerCommand() {
        super("server");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            commandSender.sendMessage(new TextComponent(MessageUtil.chat_no_connect_darta));
            return;
        }
        if (strings.length == 1) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            String server = strings[0].toLowerCase();
            String currentServer = player.getServer().getInfo().getName();
            if (server.equalsIgnoreCase(currentServer)) {
                commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§cВы уже находитесь на этом сервере"));
            } else if (ProxyServer.getInstance().getServers().containsKey(server)) {
                player.connect(ProxyServer.getInstance().getServerInfo(server), ((aBoolean, throwable) -> {
                    if (aBoolean) {
                        player.sendMessage(new TextComponent(MessageUtil.prefix + "§6Вы успешно телепортированы на сервер §c" + server));
                    } else {
                        player.sendMessage(new TextComponent(MessageUtil.prefix + "§cНе удалось подключиться"));
                    }
                }));
            } else {
                commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§cСервер " + server + " не найден"));
            }
        } else {
            commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§cНеправильно указаны данные. Пишите /server <сервер>"));
        }
    }
}
