package org.kvlt.core.commands;

import org.kvlt.core.email.Email;

public class ReloadEmailsCommand extends Command {

    public ReloadEmailsCommand() {
        super("reloadmail");
        addAliases("rm", "reloadm", "rmails", "remail", "reloadmails");
    }

    @Override
    protected boolean execute() {
        Email.loadTemplates();
        return true;
    }
}
