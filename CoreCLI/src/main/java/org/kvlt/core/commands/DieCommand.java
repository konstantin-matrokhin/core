package org.kvlt.core.commands;

import org.kvlt.core.Core;

public class DieCommand extends Command {

    public DieCommand() {
        super("die");
        addAliases("diepls", "end", "stop");
    }

    @Override
    protected boolean execute() {
        Core.get().getServer().stop();
        System.exit(0);
        return false;
    }
}
