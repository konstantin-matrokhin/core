package net.enot.dartabungee.auth.commands;

import net.enot.dartabungee.auth.AuthPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 12.05.2017.
 */
public class PasswordCommand extends Command {

    public PasswordCommand() {
        super("password");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        AuthPlayer authPlayer = AuthPlayer.getAuthPlayer(commandSender.getName());
        if (!authPlayer.isNewPassword()){
            commandSender.sendMessage(new TextComponent("§cВы не отправляли запрос на почту по восстановлению доступа к аккаунту"));
            return;
        }
        if (strings.length == 2){
            if (strings[0].equals(strings[1])){
                commandSender.sendMessage(new TextComponent(""));
                commandSender.sendMessage(new TextComponent("           §a[Успешная авторизация под новым паролем]"));
                commandSender.sendMessage(new TextComponent(""));
                authPlayer.setPassword(strings[0]);
                authPlayer.setAuthorized(true);
                authPlayer.setNewPassword(false);
                authPlayer.setDisableSession();
            } else {
                commandSender.sendMessage(new TextComponent("§cПароли не совпадают"));
            }
        } else {
            commandSender.sendMessage(new TextComponent("§cНеправильно указаны данные. Пишите /password <новый пароль> <новый пароль>"));
        }
    }

}
