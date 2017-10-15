package net.enot.dartabungee.auth.commands;

import net.enot.dartabungee.auth.AuthPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 11.05.2017.
 */
public class CodeCommand extends Command {

    public CodeCommand() {
        super("code");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (strings.length == 1){
            AuthPlayer authPlayer = AuthPlayer.getAuthPlayer(commandSender.getName());
        } else {
            commandSender.sendMessage(new TextComponent(""));
        }
    }

}
