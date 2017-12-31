package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.utils.Printer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WhoisCommand extends Command {

    public WhoisCommand() {
        super("whois");

        addAliases(
                "info",
                "about"
        );
    }

    @Override
    protected boolean execute() {
        if (getArgs().length != 1) {
            Printer.$("Введите имя игрока.");
            return false;
        }
        String name = getArg(0);

        Runnable r = () -> {
            if (CoreServer.get().getOnlinePlayers().contains(name)) {
                ServerPlayer player = CoreServer.get().getOnlinePlayers().get(name);
                printInfo(player);
            } else {
                ServerPlayer player = PlayerFactory.loadPlayer(name, false);
                if (player != null) {
                    printInfo(player);
                } else {
                    Printer.$("Игрок не найден в БД.");
                }
            }
        };
        PlayerFactory.addTask(r);
        return true;
    }

    private void printInfo(ServerPlayer player) {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        String totalPlayed = format.format(new Date(player.getPlayedTotal()));
        String lastTimePlayed = format.format(new Date(player.getPlayedLastTime()));

        Printer.$("_____________________________________");
        Printer.$("ИГРОК: " + player.getName());
        Printer.$("ID: " + player.getId());
        Printer.$("ВСЕГО СЫГРАНО: " + totalPlayed);
        Printer.$("ПОСЛЕДНЕЕ ВРЕМЯ ОНЛАЙНА: " + lastTimePlayed);
//        Printer.$("СЕРВЕР: " + player.getCurrentServer().getName());
//        Printer.$("ПРОКСИ: " + player.getCurrentProxy().getName());
        Printer.$("IP: " + player.getIp());
        Printer.$("_____________________________________");
    }

}
