package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.db.PlayerFactory;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.utils.Printer;

import java.util.Optional;

public class BanCommand extends Command {

    public BanCommand() {
        super("ban");
        addAliases("setbanned", "b", "crucify");
    }

    @Override
    protected boolean execute() {
        if (getArgs().length < 1) {
            Printer.$("ban <игрок> [время] [причина]");
            return false;
        }

        String name = getArg(0);
        String until = Optional.ofNullable(getArg(1)).orElse("e");
        String reason = Optional.ofNullable(getArg(2)).orElse("BANNED!");
        ServerPlayer player = Optional.ofNullable(Optional
                .ofNullable(CoreServer.get().getOnlinePlayers().get(name))
                .orElse(PlayerFactory.loadPlayer(name, false)))
                .orElseThrow(IllegalArgumentException::new);

        PlayerFactory.ban(player, "CONSOLE", until, reason);
        return true;
    }
}
