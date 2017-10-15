package org.kvlt.core.bungee;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.kvlt.core.bungee.utils.Log;
import org.kvlt.core.entities.ServerPlayer;
import org.kvlt.core.packets.ProxyLoginPacket;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        /*ProxyLoginPacket packet = new ProxyLoginPacket(
                new ServerPlayer("suka"),
                CoreBungee.get().getServerName()
        );
        CoreBungee.get().getCoreServer().writeAndFlush(packet);*/
    }
}
