package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.utils.Log;

public class WhoCommand extends Command {

    public WhoCommand() {
        super("who");
    }

    @Override
    public boolean execute() {
        String players = "";
        for (OnlinePlayer op: CoreServer.get().getOnlinePlayers()) {
            players.concat(players + " ");
        }
        if (players.isEmpty()) players = "Никого нет";

        Log.$("Подключенные игроки:");
        Log.$(players);
        return true;
    }

}
