package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.utils.Printer;

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

        Printer.$("Подключенные игроки (" + CoreServer.get().getOnlinePlayers().size() + ") :");
        Printer.$(players.toString().isEmpty() ? "Никого нет" : players.toString());
        return true;
    }

}
