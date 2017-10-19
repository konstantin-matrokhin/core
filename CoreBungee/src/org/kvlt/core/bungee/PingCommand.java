package org.kvlt.core.bungee;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.entities.SimplePlayer;
import org.kvlt.core.packets.ProxyLoginPacket;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        CoreBungee.get().getCoreServer().writeAndFlush(new ProxyLoginPacket(new SimplePlayer(), "a"));
    }
}
