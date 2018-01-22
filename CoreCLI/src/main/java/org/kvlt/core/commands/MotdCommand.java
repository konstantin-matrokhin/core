package org.kvlt.core.commands;

import org.kvlt.core.config.Config;
import org.kvlt.core.packets.Destination;
import org.kvlt.core.packets.proxy.MotdPacket;

public class MotdCommand extends Command {

    public MotdCommand() {
        super("motd");
        addAliases("motdreload");
    }

    @Override
    protected boolean execute() {
        Config.init();
        String motd = Config.getCore("motd").getAsString();
        new MotdPacket(motd).send(Destination.BUNGEE);
        return true;
    }
}
