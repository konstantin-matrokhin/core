package net.enot.dartabungee.auth.commands;

import net.enot.dartabungee.auth.AuthPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 11.05.2017.
 */
public class ChangePasswordCommand extends Command {

    public ChangePasswordCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (strings.length == 2){
            AuthPlayer authPlayer = AuthPlayer.getAuthPlayer(commandSender.getName());
            if (!authPlayer.getPassword().equals(strings[0])){
                commandSender.sendMessage(new TextComponent("§cНеправильно указан старый пароль"));
                return;
            }
            authPlayer.setPassword(strings[1]);
            authPlayer.setDisableSession();
            commandSender.sendMessage(new TextComponent("§6Вы успешно сменили пароль"));
        } else {
            commandSender.sendMessage(new TextComponent("§cНеправильно указаны данные. Пишите /changepassword <старый пароль> <новый пароль>"));
        }
    }

}
