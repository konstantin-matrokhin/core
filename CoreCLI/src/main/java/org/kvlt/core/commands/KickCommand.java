package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.packets.player.KickPacket;

import java.util.Optional;

public class KickCommand extends Command {

    public KickCommand() {
        super("kick");
    }

    @Override
    protected boolean execute() {
        if (getArgs().length < 1) return false;

        String name = getArg(0);
        Optional<String> reason = Optional.ofNullable(getArg(1));

        ServerPlayer victim = CoreServer.get().getOnlinePlayers().get(getArg(0));
        if (victim != null) {
            new KickPacket(name, reason.orElse("Kicked from CORE!"))
                    .send(Destination.BUNGEE);
        }

        return true;
    }

}
