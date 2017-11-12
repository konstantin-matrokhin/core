package org.kvlt.core.commands;

import org.kvlt.core.entities.ServerPlayer;

public class WhoisCommand extends Command {

    public WhoisCommand() {
        super("whois");
        addAliases(
                "info",
                "about"
        );
    }

    @Override
    protected boolean execute() {
        if (getArgs().length != 1) return false;
        //TODO continue;
        return true;
    }
}
