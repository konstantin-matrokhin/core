package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.utils.Log;

public class WhoCommand extends Command {

    public WhoCommand() {
        super("who");
    }

    @Override
    public boolean execute() {
        final StringBuilder players = new StringBuilder();
        CoreServer.get().getOnlinePlayers().forEach(p -> {
            players.append(p.getName()).append(" ");
        });

        Log.$("Подключенные игроки (" + CoreServer.get().getOnlinePlayers().size() + ") :");
        Log.$(players.toString().isEmpty() ? "Никого нет" : players.toString());
        return true;
    }

}
