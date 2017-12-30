package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.packets.EmailAddPacket;
import org.kvlt.core.bungee.packets.EmailVerifyPacket;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAddCommand extends Command {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final int CODE_LENGTH = 5;


    public EmailAddCommand() {
        super("email");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer player;

        if (args.length < 2) return;

        if (sender instanceof ProxiedPlayer) {
            player = (ProxiedPlayer) sender;
        } else {
            return;
        }

        String name = player.getName();
        String action = args[0].toLowerCase();

        switch (action) {
            case "add":
                String email = args[1];
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
                if (matcher.find()) {
                    new EmailAddPacket(name, email).send();
                    player.sendMessage(new TextComponent(String.format("На ваш email %s отправлено письмо с кодом.", email)));
                    player.sendMessage(new TextComponent("Введите команду /email verify [код] для подтверждения почты."));
                } else {
                    player.sendMessage(new TextComponent("Введите корректный email!"));
                    return;
                }
                break;
            case "verify":
                String code = args[1];
                if (code.length() == CODE_LENGTH) {
                    new EmailVerifyPacket(name, code).send();
                }
                break;
            default:
                player.sendMessage(new TextComponent("Некорректная команда!"));
                break;
        }

    }
}
