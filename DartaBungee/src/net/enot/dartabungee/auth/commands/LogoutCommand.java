package net.enot.dartabungee.auth.commands;

import net.enot.dartabungee.auth.AuthPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Енот on 11.05.2017.
 */
public class LogoutCommand extends Command {

    public LogoutCommand() {
        super("logout");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        AuthPlayer.getAuthPlayer(player.getName()).setDisableSession();
        player.disconnect(new TextComponent("§cВы вышли"));
    }

}
