package net.enot.dartabungee.client.commands;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 24.04.2017.
 */
public class WhereCommand extends Command{

    public WhereCommand(String command) {
        super(command);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            commandSender.sendMessage(new TextComponent(MessageUtil.chat_no_connect_darta));
            return;
        }
        if (strings.length == 0){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            String currentServer = player.getServer().getInfo().getName();
            commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§6Вы находитесь на сервере §c" + currentServer));
        } else {
            commandSender.sendMessage(new TextComponent(MessageUtil.prefix + "§cНеправильно указаны данные. Пишите /whe или /where"));
        }
    }
}
