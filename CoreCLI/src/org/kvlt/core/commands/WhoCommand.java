package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class WhoCommand extends Command {

    public WhoCommand() {
        super("who");
    }

    @Override
    public boolean execute() {
        String players = "";
        for (OnlinePlayer op: CoreServer.get().getOnlinePlayers()) {
            players += op.getName() + " ";
        }

        Log.$("Подключенные игроки (" + CoreServer.get().getOnlinePlayers().size() + " ):");
        Log.$(players.isEmpty() ? "Никого нет" : players);
        return true;
    }

}
