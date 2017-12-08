package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;

public class StopCommand extends Command {

    public StopCommand() {
        super("stop");
    }

    @Override
    protected boolean execute() {
        CoreServer.get().stop();
        System.exit(0);
        return false;
    }
}
