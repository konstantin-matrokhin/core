package org.kvlt.core.bungee;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.utils.Log;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        BungeeCord.getInstance().getLogger().info(CoreBungee.get().getPingEventListener().getMotd());
    }
}
