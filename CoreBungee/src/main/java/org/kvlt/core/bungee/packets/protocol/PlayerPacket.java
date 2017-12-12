package org.kvlt.core.bungee.packets.protocol;

public abstract class PlayerPacket extends BungeeOutPacket {

    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
