package org.kvlt.core.bungee.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.packets.*;
import org.kvlt.core.bungee.storages.ProxyLoggedPlayers;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCommand extends Command {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final int CODE_LENGTH = 5;


    public EmailCommand() {
        super("email");
    }

    private boolean isEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer player;

        if (args.length < 1) {
            sender.sendMessage(new TextComponent("/email <add/verify/confirm/recovery> [email]"));
            return;
        }

        if (sender instanceof ProxiedPlayer) {
            player = (ProxiedPlayer) sender;
        } else {
            return;
        }

        String name = player.getName();
        String action = args[0].toLowerCase();
        Optional<String> email = Optional.ofNullable(args[1]);

        switch (action) {
            case "add":
                if (!email.isPresent()) return;

                if (!ProxyLoggedPlayers.isLogged(name)) {
                    player.sendMessage(new TextComponent("Сначала залогиньтесь!"));
                    return;
                }
                if (isEmail(email.get())) {
                    new EmailAddPacket(name, email.get()).send();
                } else {
                    player.sendMessage(new TextComponent("Введите корректный email!"));
                }
                break;
            case "verify":
                if (!ProxyLoggedPlayers.isLogged(name)) {
                    player.sendMessage(new TextComponent("Сначала залогиньтесь!"));
                    return;
                }

                String code = args[1];
                if (code == null) return;

                Optional<String> oCode2 = Optional.ofNullable(args[2]);

                if (oCode2.isPresent()) {
                    String code2 = oCode2.get();
                    if (code.length() == CODE_LENGTH && code2.length() == CODE_LENGTH) {
                        new EmailChangeVerifyPacket(name, code, code2).send();
                    } else {
                        player.sendMessage(new TextComponent("Коды состоят из 5 латинских букв!"));
                    }
                } else {
                    if (code.length() == CODE_LENGTH) {
                        new EmailVerifyPacket(name, code).send();
                    } else {
                        player.sendMessage(new TextComponent("Код состоит из 5 латинских букв!"));
                    }
                }
                break;
            case "change":
                if (!ProxyLoggedPlayers.isLogged(name)) {
                    player.sendMessage(new TextComponent("Сначала залогиньтесь!"));
                    return;
                }
                if (!email.isPresent()) return;

                String newMail = args[2];
                if (!email.get().equalsIgnoreCase(newMail)) {
                    if (newMail != null && isEmail(newMail)) {
                        new EmailChangePacket(name, email.get(), newMail).send();
                    }
                } else {
                    player.sendMessage(new TextComponent("Ящики совпадают!"));
                }
                break;
            case "recovery":
                new PasswordRecoveryPacket(name).send();
                break;
            default:
                player.sendMessage(new TextComponent("Некорректная команда!"));
                break;
        }

    }
}
