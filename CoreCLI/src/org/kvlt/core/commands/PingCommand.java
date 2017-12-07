package org.kvlt.core.commands;

import org.kvlt.core.packets.CorePingPacket;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    protected boolean execute() {
        CorePingPacket cpp = new CorePingPacket();
        cpp.send();
        return true;
    }
}
