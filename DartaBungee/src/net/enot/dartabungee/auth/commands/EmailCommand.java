package net.enot.dartabungee.auth.commands;

import net.enot.dartabungee.auth.AuthPlayer;
import net.enot.dartabungee.utils.RegexUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 12.05.2017.
 */
public class EmailCommand extends Command {

    public EmailCommand() {
        super("email");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        AuthPlayer authPlayer = AuthPlayer.getAuthPlayer(commandSender.getName());
        if (strings.length > 0){
            switch (strings[0]){
                case "add":
                    if (strings.length == 3){
                        if (strings[1].equals(strings[2])){
                            if (RegexUtil.isMail(strings[1])){
                                //TODO Send code
                            } else {
                                commandSender.sendMessage(new TextComponent("§cНеправильный формат почты"));
                            }
                        } else {
                            commandSender.sendMessage(new TextComponent("§cПочты не совпадают"));
                        }
                    } else {
                        commandSender.sendMessage(new TextComponent("§cНеправильно указаны данные. Пишите /email add <почта> <почта>"));
                    }
                    return;
                case "change":
                    if (strings.length == 3){
                        if (strings[1].equals(authPlayer.getMail())){
                            if (RegexUtil.isMail(strings[1])){
                                //TODO Send code
                            } else {
                                commandSender.sendMessage(new TextComponent("§cНеправильный формат почты"));
                            }
                        } else {
                            commandSender.sendMessage(new TextComponent("§cНеправильно указана старая почта"));
                        }
                    } else {
                        commandSender.sendMessage(new TextComponent("§cНеправильно указаны данные. Пишите /email change <старая почта> <новая почта>"));
                    }
                    return;
                case "recovery":
                    if (strings.length == 2){
                        if (authPlayer.hasMail() && authPlayer.getMail().equals(strings[1])){
                            //TODO Send code
                        } else {
                            commandSender.sendMessage(new TextComponent("§cНеправильная почта"));
                        }
                    } else {
                        commandSender.sendMessage(new TextComponent("§cНеправильно указаны данные. Пишите /email recovery <почта>"));
                    }
                    return;
            }
        }
        commandSender.sendMessage(new TextComponent("§cНеправильно указаны данные. Доступные команды:"));
        commandSender.sendMessage(new TextComponent(" §cПривязать почту - /email add <почта> <почта>"));
        commandSender.sendMessage(new TextComponent(" §cСменить почту - /email change <старая почта> <новая почта>"));
        commandSender.sendMessage(new TextComponent(" §cВосстановить пароль - /email recovery <почта>"));
    }

}
