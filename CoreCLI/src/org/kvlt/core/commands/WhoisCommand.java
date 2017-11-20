package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.utils.Log;
import org.kvlt.core.utils.LogType;
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
        if (getArgs().length != 1) return false;
        String name = getArg(0);

        Runnable r = () -> {
            if (CoreServer.get().getOnlinePlayers().contains(name)) {
                OnlinePlayer player = CoreServer.get().getOnlinePlayers().get(name);
                printInfo(player);
            } else {
                ServerPlayer player = PlayerDB.loadServerPlayer(name);
                if (player != null) {
                    printInfo(player);
                } else {
                    Log.$(LogType.ERROR, "Игрок не найден в БД.");
                }
            }
        };
        PlayerDB.executor.execute(r);
        return true;
    }

    private void printInfo(ServerPlayer player) {
        boolean isOnline = false;
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        String totalPlayed = format.format(new Date(player.getPlayedTotal()));
        String lastTimePlayed = format.format(new Date(player.getPlayedLastTime()));

        OnlinePlayer op = null;
        if (player instanceof OnlinePlayer) {
            op = (OnlinePlayer) player;
            isOnline = true;
        }

        Printer.$("_____________________________________");
        Printer.$("ИГРОК: " + player.getName());
        Printer.$("ID: " + player.getId());
        Printer.$("ВСЕГО СЫГРАНО: " + totalPlayed);
        Printer.$("ПОСЛЕДНЕЕ ВРЕМЯ ОНЛАЙНА: " + lastTimePlayed);
        if (isOnline) {
            Printer.$("ОНЛАЙН: ДА");
            Printer.$("СЕРВЕР: " + op.getCurrentServer());
            Printer.$("ПРОКСИ: " + op.getCurrentProxy());
            Printer.$("IP: " + op.getIp());
        } else {
            Printer.$("ОНЛАЙН: НЕТ");
        }
        Printer.$("_____________________________________");
    }

}
