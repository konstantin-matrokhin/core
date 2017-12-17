package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;

public class DieCommand extends Command {

    public DieCommand() {
        super("die");
        addAliases("diepls");
    }

    @Override
    protected boolean execute() {
        CoreServer.get().stop();
        System.exit(0);
        return false;
    }
}
