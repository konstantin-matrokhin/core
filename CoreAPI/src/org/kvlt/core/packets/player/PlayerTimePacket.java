package org.kvlt.core.packets.player;

import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.metrics.PlayedTimeCounter;
import org.kvlt.core.packets.PacketOld;
import org.kvlt.core.packets.bukkit.ServerMessagePacket;

public class PlayerTimePacket extends PacketOld {

    public String playerName;

    public PlayerTimePacket(String playerName) {
        this.playerName = playerName;
    }

    @Override
    protected void onCore() {
        OnlinePlayer player = CoreServer.get().getOnlinePlayers().get(playerName);
        String recipient = playerName;
        String time = PlayedTimeCounter.getFormatedTime(player.getPlayedTotal());

        ServerMessagePacket smp = new ServerMessagePacket(recipient, time);
        player.getCurrentServer().send(smp);
    }

    @Override
    protected void onServer() {

    }

    @Override
    protected void onProxy() {

    }
}
