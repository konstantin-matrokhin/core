package org.kvlt.core.commands;

import org.kvlt.core.Core;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.packets.player.KickPacket;
import org.kvlt.core.utils.Printer;

import java.util.Optional;

public class KickCommand extends Command {

    public KickCommand() {
        super("kick");
    }

    @Override
    protected boolean execute() {
        if (getArgs().length < 1) {
            Printer.$("Укажите игрока!");
            return false;
        }

        String name = getArg(0);
        Optional<String> reason = Optional.ofNullable(getArg(1));

        ServerPlayer victim = Core.get().getOnlinePlayers().get(getArg(0));
        if (victim != null) {
            new KickPacket(name, reason.orElse("Kicked from CORE!"))
                    .send(Destination.BUNGEE);

            Printer.$(String.format("%s кикнут с сервера %s: %s",
                    name,
                    victim.getCurrentProxy().getName(),
                    reason));
        } else {
            Printer.$("Игрок не найден!");
        }

        return true;
    }

}
