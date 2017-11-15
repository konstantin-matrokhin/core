package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.packets.Packet;
import org.kvlt.core.packets.bukkit.ServerMessagePacket;

public class PlayerTimePacket extends Packet {

    public OnlinePlayer player;

    public PlayerTimePacket(OnlinePlayer player) {
        this.player = player;
    }

    @Override
    protected void onCore() {
        OnlinePlayer realPlayer = CoreServer.get().getOnlinePlayers().get(player.getName());
        String recipient = realPlayer.getName();
        String time = PlayedTimeCounter.getFormatedTime(realPlayer.getPlayedTotal());

        ServerMessagePacket smp = new ServerMessagePacket(recipient, time);
        realPlayer.getCurrentServer().send(smp);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }
}
