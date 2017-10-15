package net.enot.dartabungee.auth.commands;

import net.enot.dartabungee.DartaBungee;
import net.enot.dartabungee.auth.AuthPlayer;
import net.enot.dartasystem.utils.MessageUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 01.05.2017.
 */
public class LoginCommand extends Command {

    public LoginCommand(String command) {
        super(command);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!DartaBungee.getInstance().getDartaClient().isConnected()){
            commandSender.sendMessage(new TextComponent(MessageUtil.chat_no_connect_darta));
            return;
        }
        AuthPlayer authPlayer = AuthPlayer.getAuthPlayer(commandSender.getName());
        if (authPlayer.isAuthorized()){
            commandSender.sendMessage(new TextComponent("§cВы уже авторизовались"));
            return;
        }
        if (!authPlayer.isRegistered()){
            commandSender.sendMessage(new TextComponent("§cВы еще не зарегистрировались. Пишите /register <пароль> <пароль>"));
            return;
        }
        if (strings.length == 1){
            if (strings[0].equals(authPlayer.getPassword())){
                commandSender.sendMessage(new TextComponent(""));
                commandSender.sendMessage(new TextComponent("                   §a[Успешная авторизация]"));
                commandSender.sendMessage(new TextComponent(""));
                authPlayer.setAuthorized(true);
            } else {
                commandSender.sendMessage(new TextComponent("§cНеправильный пароль"));
            }
        } else {
            commandSender.sendMessage(new TextComponent("§cНеправильно указаны данные. Пишите /login <пароль>"));
        }
    }
}
