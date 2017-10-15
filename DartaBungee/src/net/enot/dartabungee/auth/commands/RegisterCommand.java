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
public class RegisterCommand extends Command {

    public RegisterCommand(String command) {
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
            commandSender.sendMessage(new TextComponent("§cВы уже зарегистрировались"));
            return;
        }
        if (authPlayer.isRegistered()){
            commandSender.sendMessage(new TextComponent("§cВы уже зарегистрированы, авторизуйтесь командой /login <пароль>"));
            return;
        }
        if (strings.length == 2){
            if (strings[0].equals(strings[1])){
                commandSender.sendMessage(new TextComponent(""));
                commandSender.sendMessage(new TextComponent("                   §a[Успешная регистрация]"));
                commandSender.sendMessage(new TextComponent(""));
                commandSender.sendMessage(new TextComponent("§6Рекомендуем привязать почту, чтобы в случаи потери пароля\n§6вы всегда могли его восстановить. Пишите §c/email add <почта> <почта>"));
                commandSender.sendMessage(new TextComponent(""));
                authPlayer.setPassword(strings[0]);
                authPlayer.setAuthorized(true);
            } else {
                commandSender.sendMessage(new TextComponent("§cПароли не совпадают"));
            }
        } else {
            commandSender.sendMessage(new TextComponent("§cНеправильно указаны данные. Пишите /register <пароль> <пароль>"));
        }
    }

}
