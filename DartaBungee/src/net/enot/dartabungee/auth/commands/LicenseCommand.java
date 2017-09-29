package net.enot.dartabungee.auth.commands;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartabungee.auth.AuthPlayer;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 02.05.2017.
 */
public class LicenseCommand extends Command {

    public LicenseCommand() {
        super("license");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            commandSender.sendMessage(new TextComponent(MessageUtil.chat_no_connect_darta));
            return;
        }
        if (strings.length == 0){
            AuthPlayer player = AuthPlayer.getAuthPlayer(commandSender.getName());
            if (player.isLicense()){
                commandSender.sendMessage(new TextComponent("§cАвторизация по лицензии уже включена"));
                return;
            }
            //TODO Send code
        } else {
            commandSender.sendMessage(new TextComponent("§cНеправильно указаны данные. Пишите /license"));
        }
    }

}
