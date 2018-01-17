package org.kvlt.core.bungee.packets.protocol;

public abstract class PlayerPacket extends BungeePacketOut {

    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
