package org.kvlt.core.bukkit.packets;

import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.protocol.PacketOut;

public abstract class BukkitPacketOut implements PacketOut {

    public void send() {
        CorePlugin.getAPI().getServer().writeAndFlush(this);
    }

}
