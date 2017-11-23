package org.kvlt.core.packets.player;

import org.bukkit.Bukkit;
import org.kvlt.core.CoreServer;
import org.kvlt.core.entities.OnlinePlayer;
import org.kvlt.core.packets.Packet;

public class PlayerKickPacket extends Packet {

    private String initiator;
    private String victim;

    public PlayerKickPacket(String initiator, String victim) {
        this.initiator = initiator;
        this.victim = victim;
    }

    @Override
    protected void onCore() {
        OnlinePlayer victimPlayer = CoreServer.get().getOnlinePlayers().get(victim);
        OnlinePlayer initiatorPlayer = CoreServer.get().getOnlinePlayers().get(initiator);

        if (victimPlayer == null || initiatorPlayer == null) return;

        if (initiatorPlayer.getGroup() > 1) {
            victimPlayer.getCurrentServer().send(this);
        }
    }

    @Override
    protected void onServer() {
        Bukkit.getPlayer(victim).kickPlayer("kicked");
        //TODO staff message
    }

    @Override
    protected void onProxy() {

    }
}
