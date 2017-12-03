package org.kvlt.core.commands;

import org.kvlt.core.CoreServer;
import org.kvlt.core.packets.bukkit.BroadcastPacketOld;
import org.kvlt.core.utils.Log;
import org.kvlt.core.utils.LogType;

public class BroadcastCommand extends Command {

    public BroadcastCommand() {
        super("bc");
    }

    @Override
    protected boolean execute() {
        String str = getArg(0);
        if (str.isEmpty()) return false;

        BroadcastPacketOld bp = new BroadcastPacketOld(str);
        CoreServer.get().getGameServers().send(bp);
        Log.$(LogType.BROADCAST, str);
        return true;
    }
}
