package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerDB;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.entities.PlayerModel;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.utils.Log;
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
        ServerPlayer player = PlayerDB.loadServerPlayer(name);

        printInfo(player);

        return true;
    }

    private void printInfo(ServerPlayer player) {
        SimpleDateFormat format = new SimpleDateFormat("HH чаcов, mm минут, ss секунд");
        String totalPlayed = format.format(new Date(player.getPlayedTotal() * 1000));
        String lastTimePlayed = format.format(new Date(player.getPlayedLastTime() * 1000));

        Printer.$("_____________________________________");
        Printer.$("ИГРОК: " + player.getName());
        Printer.$("ID: " + player.getId());
        Printer.$("ВСЕГО СЫГРАНО: " + totalPlayed);
        Printer.$("ПОСЛЕДНЕЕ ВРЕМЯ ОНЛАЙНА: " + lastTimePlayed);
        Printer.$("_____________________________________");
    }

}
