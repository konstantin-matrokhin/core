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
        final String players = "";
        CoreServer.get().getOnlinePlayers().forEach(op -> players.concat(op + " "));

        Log.$("Подключенные игроки:");
        Log.$(players.isEmpty() ? "Никого нет" : players);
        return true;
    }

}
