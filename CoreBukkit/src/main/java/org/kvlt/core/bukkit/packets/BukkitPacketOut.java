package org.kvlt.core.bukkit.packets;

import org.kvlt.core.bukkit.CorePlugin;
import org.kvlt.core.protocol.PacketOut;

import java.util.Random;

public abstract class BukkitPacketOut implements PacketOut {

    protected static final Random random = new Random();

    public void send() {
        CorePlugin.getAPI().getPlayerInfo().writeAndFlush(this);
    }

}
