package org.kvlt.core.commands;

import org.kvlt.core.Core;
import org.kvlt.core.utils.Printer;

public class WhoCommand extends Command {

    public WhoCommand() {
        super("who");
    }

    @Override
    public boolean execute() {
        final StringBuilder onlinePlayers = new StringBuilder();
        final StringBuilder unloggedPlayers = new StringBuilder();

        Core.get().getOnlinePlayers().forEach(p -> onlinePlayers.append(p.getName()).append(" "));
        Core.get().getUnloggedPlayers().forEach(p -> unloggedPlayers.append(p.getName()).append(" "));

        Printer.$("Подключенные игроки(" + Core.get().getOnlinePlayers().size() + "):");
        Printer.$(onlinePlayers.toString().isEmpty() ? "Никого нет" : onlinePlayers.toString());
        Printer.$("");
        Printer.$("Незалогиневшиеся игроки(" + Core.get().getUnloggedPlayers().size() + "):");
        Printer.$(unloggedPlayers.toString().isEmpty() ? "Никого нет" : unloggedPlayers.toString());

        return true;
    }

}
