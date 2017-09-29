package net.enot.dartabungee.auth.commands;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartabungee.auth.AuthPlayer;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 11.05.2017.
 */
public class UnlicenseCommand extends Command {

    public UnlicenseCommand() {
        super("unlicense");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            commandSender.sendMessage(new TextComponent(MessageUtil.chat_no_connect_darta));
            return;
        }
        if (strings.length == 0){
            AuthPlayer player = AuthPlayer.getAuthPlayer(commandSender.getName());
            if (!player.isLicense()){
                commandSender.sendMessage(new TextComponent("§cАвторизация по лицензии еще не активирована"));
                return;
            }
            player.setLicense(false);
            commandSender.sendMessage(new TextComponent("§6Авторизация по лицензии отключена"));
        } else {
            commandSender.sendMessage(new TextComponent("§cНеправильно указаны данные. Пишите /unlicense"));
        }
    }

}
